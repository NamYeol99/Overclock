<template>
  <header id="header" class="fixed-top ">
    <div class="container d-flex align-items-center justify-content-lg-between">  
      <h1 class="logo me-auto me-lg-0"><router-link to="/">OverClock<span>.</span></router-link></h1>
      <nav id="navbar" class="navbar order-last order-lg-0">
        <h5 class="logo logo2 me-auto me-lg-0"><router-link to="/auction" style="text-decoration-line: none;">OverClock<span>+</span></router-link></h5>
        <ul>
          <li><router-link to="/parts" class="nav-link scrollto" >부품</router-link></li>
          <li><router-link to="/peri" class="nav-link scrollto" >주변기기</router-link></li>
          <li><router-link to="/used" class="nav-link scrollto" >중고장터</router-link></li>
          <li><router-link to="/free" class="nav-link scrollto" >자유게시판 </router-link></li>
          <li><router-link to="/qna" class="nav-link scrollto" >고객센터 </router-link></li>
          <li><a class="nav-link scrollto" href="#contact">찾아오시는 길</a></li>
          <li class="dropdown"><a href="#">
            <i class="bi bi-person-square" style="font-size:x-large"></i>
            <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><router-link to="/profil" v-if="$store.state.token">내정보</router-link></li>
              <li><a :href="'./cart?id=' + memberId" v-if="$store.state.token">장바구니</a></li>
              <li><router-link to="/login" v-if="!$store.state.token">로그인</router-link></li>
              <li><router-link to="/" @click="logout()" v-if="$store.state.token">
                로그아웃</router-link></li>
              <li><router-link to="/join" v-if="!$store.state.token">회원가입</router-link></li>
            </ul>
          </li> 
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->
    </div>
  </header>
  <router-view/>
</template>
  
<script>
import router from './router';
import store from './store';
export default {
    name: "App",
    setup() {
      const memberId = store.state.id;
      const logout =()=>{
      store.commit('setdtoList',"");
      store.commit('setToken',0);
      store.commit('setId',0);
      store.commit('setEmail',0);
      store.commit("setRole", "")

      alert("로그아웃 되었습니다")

      router.push({path:"/"})
    }
      return{logout,memberId}
    },

}

</script>

<style>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");
#header{
  background: black;
}
li a{
  text-decoration-line: none;
}
</style>
