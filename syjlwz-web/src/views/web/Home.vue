<template>
  <div >
   
     <div class="swiper-container swiper-container1">
          <div class="swiper-wrapper">
            <div class="swiper-slide">
              <img style="width: 100%; height: 405px" :src="banner1" />
            </div>
            <div class="swiper-slide">
              <img style="width: 100%; height: 405px" :src="banner2" />
            </div>
          </div>
        </div>





<div class="container" id="content-container">
 
		 <h2 style="  font-size: 24px; position: relative;  text-align: center;  margin: 60px auto 35px;">
      <strong>最新上传</strong>
    </h2>


			<div class="row co-classify-list">
				<div class="col-md-12">
 

					<div class="course list row">

             
			    <div class="col-md-3 col-xs-5" v-for="item in zplist" :key="item.id">
							<div class="course-item " >
								<div class="course-img" style="width: 270px;height: 150px;">
									<a   :href="'/zpinfoView?id=' + item.id"> 
                  
                     <img :src="'http://localhost:8088/syjlwz/'+item.fujian"   />
									</a>
								</div>
								<div class="course-info">
									<div class="title text-o-show">
										<a   :href="'/zpinfoView?id=' + item.id">{{
                  item.name
                }}</a>
									</div>
									<div class="metas">
										 {{item.uinfoVO.username}}
									</div>
									
									
						 
                              
                              
                              
								</div>
							</div>
						</div>
             


						 
 

					</div>
				</div>
			       
 
			</div>
		</div>





    <footer class="footer">
  <div class="container">
    <div class="row">
      <div class="col-md-4 ">
      <a href="#"> </a>
      </div>
      <div class="col-md-4 about">
        <span><a href="#">关于我们</a></span>|
        <span><a href="#" target="_blank">加入我们</a></span>|
        <span><a href="#" target="_blank">合作申请</a></span>|
        <span><a target="_blank" href="/login">网站后台</a></span><br>
          <a class="text-sm con prm" href="#"  >
              ICP备88888888号 
          </a>
          <a class="text-sm con plm" href="#"  >
            B2-202000001
          </a>
              </div>
  
              
          
    </div>
  </div>
</footer>


  </div>
</template>

<script>
import request, { base } from "../../../utils/http";
import Swiper from "swiper";
import "swiper/dist/css/swiper.min.css";
import "swiper/dist/js/swiper.min";

export default {
  components: {
    Swiper,
  },
  data() {
    return {
      banner1: require("@/assets/images/2222.png"),
      banner2: require("@/assets/images/3333.png"),
      zplist: [],
       
    };
  },
  mounted() {
    new Swiper(".swiper-container", {
      slidesPerView: 1,
      spaceBetween: 0,
      loop: true,
      autoplay: 3000,
    });
  },
  created() {
    this.getZpTopList();
 
  },
  methods: {
    getZpTopList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/zpinfo/newlist";
      request.post(url, para).then((res) => {
        this.zplist = res.resdata;
      });
    },
   
  },
};
</script>


<style  scoped>
 
</style>

 
