<template>
  <section id="services2" class="services">
    <div class="container" data-aos="fade-up">
      <router-link to="/periregister" class="btn btn-primary" style="float:right;" v-if="(store.state.role == '1')">글쓰기
      </router-link>
      <div>
        <form class="searching-area d-flex align-items-center gap-1 w-50 mt-3" @submit.prevent="searchingAxios()">
          <label for="searching"><i class="bi bi-search btn btn-primary"></i></label>
          <input id="searching" v-model="search.context" type="text" class="form-control bg-white"
            @submit="searchingAxios()">
        </form>
      </div>
      <div class="section-title">
        <p>MOUSE</p>
      </div>
      <div class="row">
        <div class="col-lg-4 col-md-6  align-items-stretch" data-aos="zoom-in" data-aos-delay="100"
          v-for="(list,i) in state.dtoList" :key="(list,i)">
          <div class="icon-box" @click="Join(list)">
            <div class="icon"><img v-bind:src="state.img[i]" /></div>
            <br><br>
            <h3>{{list.title}}</h3>
            <span>
              <h5>판매가: {{state.price[i]}}</h5>
            </span>
          </div>
        </div>
        <div class="page">
          <ul class="pagination">
            <li class="page-item"><a class="page-link" @click="getUserList(state.page-1)" v-if="state.page!=1">Prev</a>
            </li>
            <li :class="state.page == page?'page-item active':'page-item'" v-for="page in state.pageList" :key="page"><a
                class="page-link" @click="getUserList(page)">{{page}}</a></li>
            <li class="page-item"><a class="page-link" @click="getUserList(state.page+1)"
                v-if="state.page!=state.totalPage">Next</a></li>
          </ul>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { reactive } from '@vue/reactivity';
import axios from "axios";
import { useStore } from 'vuex';
import { useRouter } from "vue-router";
import { useMeta } from "vue-meta";

export default {
  name: 'PeriMouse',
  setup() {
    const store = useStore();
    const router = useRouter()

    let search = reactive({
      context: "",
    })
    function searchingAxios() {
      console.log(search.context.trim().length);
      if (search.context.trim().length == 0) {
        return
      }
      async function routing() {
        await router.push(`/search?cards=${search.context}&postsType=${state.partsType}`);

      }
      routing();
    }
    const { meta } = useMeta({
      title: ':: OverClock',
    })
    const state = reactive({
      id: "",
      upResult: "",
      img: [],
      dtoList: [],
      end: null,
      next: null,
      page: null,
      pageList: null,
      prev: null,
      size: null,
      start: null,
      totalPage: null,
      partsType: "mouse",
      price: [],
      itemDetail: "",
    });
    const url = "./api/periList";
    const headers = {
      "Content-Type": "application/json; charset=utf-8",
    };
    function getUserList(page) {
      axios.post(url, { page: page, type: "", category: "mouse" }, { headers })
        .then(function (res) {
          state.id = res.data.dtoList.id,
            state.dtoList = res.data.dtoList,
            state.end = res.data.end,
            state.next = res.data.next,
            state.page = res.data.page,
            state.pageList = res.data.pageList,
            state.prev = res.data.prev,
            state.size = res.data.size,
            state.start = res.data.start,
            state.totalPage = res.data.totalPage
          showResult(res.data)
        })
    }
    axios.post(url, { page: 1, category: "mouse" }, { headers })
      .then(function (res) {
        state.dtoList = res.data.dtoList,
          state.end = res.data.end,
          state.next = res.data.next,
          state.page = res.data.page,
          state.pageList = res.data.pageList,
          state.prev = res.data.prev,
          state.size = res.data.size,
          state.start = res.data.start,
          state.totalPage = res.data.totalPage;
        showResult(res.data);
      });

    const showResult = async (arr) => {
      const displayUrl = "/overclock/display";
      const url = `http://localhost:9090${displayUrl}`;
      let str2 = "";
      for (let i = 0; i < arr.dtoList.length; i++) {
        str2 = `${url}?fileName=${arr.dtoList[i].imageDTOList[0].thumbnailURL}`;
        state.img[i] = str2;
      }
    };
    const body = {
      partsType: "mouse"
    }

    axios.post("./api/partsItemList", body, { headers }).then(function (res) {
      for (let i = 0; i < res.data.length; i++) {
        state.price[i] = res.data[i];
        // state.itemDetail = res.data[i].itemDetail;
      }
    })
    console.log(state.id);
    async function Join(list) {
      //조회수 처리
      const url2 = `./api/read/${list.id}`;
      const headers2 = {
        "Content-Type": "application/json; charset=utf-8"
      };

      axios.get(url2, { page: 1, category: "mouse" }, { headers2 }).then(function (res) {
        console.log(res);
      })
      await router.push(`/peridetail?id=${list.id}`)
    }
    return { state, store, getUserList, Join, search, searchingAxios, meta }
  }
}
</script>

<style scoped>
.pagination {
  width: 100px;
  margin: auto;
}

.page {
  margin-top: 30px;
}

a {
  text-decoration-line: none;
}

.services .icon-box {
  border: 1px solid rgb(102, 102, 102);
  border-radius: 10px;
}

#searching {
  border: 1px solid black;
}
</style>