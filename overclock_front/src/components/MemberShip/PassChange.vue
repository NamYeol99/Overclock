<template>
  <div class="input-form2 col-md-12 mx-auto">
  <form class="d-flex flex-column validation-form" action="login" method="post">
    <span class="answer text-center" i-d-b-database="answerForRecovery1">새로운 비밀번호</span>
    <input class="form-control" type="password" placeholder="새로운 비밀번호" v-model="state.password" required autofocus/>
    <input class="form-control" type="password" name="repassword" id="repassword" placeholder="비밀번호 확인"
      v-model="state.repassword" required autofocus/>
    <div class="px-5 my-4">
      <button type="button" class="btn btn-outline-primary w-100" @click="change()">비밀번호 변경</button>
    </div>
  </form>
</div>
</template>
<script>
import { reactive } from '@vue/reactivity'
import axios from 'axios'
import router from '@/router'
import store from '@/store'

export default {
  name: 'PassChange',
  props: ["email"],
  setup(props) {
    const state = reactive({
      id: "",
      password: "",
      repassword: "",
      email: props.email
    })
    console.log(store.state.dtoList)
    console.log(state.email);

    const headers = {
      "Content-Type": "application/json",
    }
    const body = {
      email: state.email
    }
    axios.post("/api/passFind/email", body, { headers }).then(function(res){
      console.log("========================");
      state.id = res.data.id
    })

    const change = async () => {
      const headers = {
        "Content-Type": "application/json",
      }
      const body = {
        id : state.id,
        password: state.password,
        repassword: state.repassword
      }
      console.log(state.password);
      const url = '/api/passFind/passChange'
      await axios.post(url, body, { headers })
      if (state.password === '') {
        alert('비밀번호를 입력해주세요.');
        return false;
      } else if (state.password !== state.repassword) {
        alert('비밀번호가 일치하지 않습니다.')
        return false;
      }
      alert('비밀번호가 성공적으로 변경되었습니다.')
      router.push({ name: "Login" })
    }
    return { change, state }
  }
}

</script>
<style>
.input-form2 {
    margin-top: 50px;
        text-align: center;
        padding: 32px;
  
        background: #fff;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
      }
</style>