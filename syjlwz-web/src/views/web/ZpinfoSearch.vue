<template>
  <div class="hzco-classify" style="margin-top:-30px;">
 
<div class="container" id="content-container" >
 
 

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
			       
 
 <div align="center">
              <pagination
                :num="num"
                :limit="limit"
                @getList="getList"
              ></pagination>
            </div>
			</div>
		</div>

    



    <footer class="footer">
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <a href="#"> </a>
          </div>
          <div class="col-md-4 about">
            <span><a href="#">关于我们</a></span
            >| <span><a href="#" target="_blank">加入我们</a></span
            >| <span><a href="#" target="_blank">合作申请</a></span
            >| <span><a target="_blank" href="/login">网站后台</a></span
            ><br />
            <a class="text-sm con prm" href="#"> ICP备88888888号 </a>
            <a class="text-sm con plm" href="#"> B2-202000001 </a>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
import request, { base } from "../../../utils/http";
import store from "../../store";
import pagination from "../../components/Pagination";
export default {
  components: {pagination},
  data() {
    return {
      zplist: "",
      num: 0,
      currentPage: 1,
      limit: 10,
      qylist: [],
      lxlist: [],
      xslist: [],
      qyid:0,
      ztid:0,
      xsid:0
    };
  },

   created() {
   
    this.qname = this.$route.query.qname;
    
     this.getList();
    
  },

 
  methods: {
    getList(value) {
      if(value!=undefined){
        if(value==0){
          this.currentPage = 1;
        }else{
           this.currentPage =  (value+this.limit)/this.limit;
        }
      }else{
        this.currentPage = 1;
      }
      let para = {
        qname:this.qname
      };
 
      let url =  base +  "/zpinfo/list?currentPage=" + this.currentPage  + "&pageSize=" + this.limit;
 
      request.post(url, para).then((res) => {
        this.zplist = res.resdata;
        this.num = res.count;   
      });
    },
     
 

  },

  
};
</script>


<style lang="less" scoped>
</style>

 
