!<template>
      <ul>
        <li><strong>상품명 </strong>: {{state.dtoList.title}}</li>
        <li><strong>판매자</strong>: {{state.companyName}} </li>
        <!-- <li><strong>등록일자</strong>: {{state.regDate}}</li> -->
        <li>
            <strong>가격</strong>: {{state.price}}
        </li>
        <li><strong>조회수</strong> : {{state.dtoList.viewCount}}</li>
      
    </ul>
</template>

<script>
import axios from 'axios';
import store from '@/store';
import { reactive } from '@vue/reactivity';
  export default {
      name: 'UsedDetail2',
      props: ["dtoList","postsDetailId"],
      setup(props){
        const state = reactive({
            dtoList: props.dtoList,
            price : store.state.price,
            postsId : props.postsDetailId,
            postsMemberId: "",
            companyName : "",
        });
        console.log(state.dtoList)
        console.log(state.postsId)
        

        // 작성자 가져오기
        const body2 = {
            id: state.postsId,
        }
        const headers = {
          "Content-Type": "application/json"
        };

        axios.post("/api/DetailName", body2, { headers }).then(function(res){
          
          console.log(res);
          console.log("============");
          state.companyName = res.data;
        //   var str = res.data.regDate;
        //   state.regDate = str.substring(0,str.indexOf('T'));
        })
        return {state}
      }
  }
</script>

<style>

</style>