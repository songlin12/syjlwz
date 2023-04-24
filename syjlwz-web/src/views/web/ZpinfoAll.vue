<template>
  <div class="hzco-classify" style="margin-top:-30px;">
 
<div class="container" id="content-container" >
 
   <div class="category">
        <ul class="tab-body tab clearfix">
          <li>
            <a    @click="setlx(0)" :class="0==ztid?' active':''" >全部类型</a >
          </li>
          <li  v-for="item in lxlist"  :key="item.id">
            <a @click="setlx(item.id)" :class="item.id==ztid?' active':''">{{item.name}}</a >
          </li>
        </ul>

        <!-- <ul class="tab-body tab clearfix">
          <li>
            <a   @click="setqy(0)" :class="0==qyid?' active':''"  >全部区域</a >
          </li>
          <li  v-for="item in qylist"  :key="item.id">
            <a @click="setqy(item.id)" :class="item.id==qyid?' active':''">{{item.name}}</a >
          </li>
        </ul>
         <ul class="tab-body tab clearfix">
          <li>
            <a  @click="setxs(0)" :class="0==xsid?' active':''"  >全部像素</a >
          </li>
          <li  v-for="item in xslist"  :key="item.id">
            <a @click="setxs(item.id)"      :class="item.id==xsid?' active':''"  >{{item.name}}</a >
          </li>
        </ul> -->
      </div>

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
    this.getList();
    store.commit("setState",1);
    this.getZpqyList();
    this.getZptypeList();
    this.getZpxsList();
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
        ztid:this.ztid,
        qyid:this.qyid,
        xsid:this.xsid
      };
 
      let url =  base +  "/zpinfo/list?currentPage=" + this.currentPage  + "&pageSize=" + this.limit;
 
      request.post(url, para).then((res) => {
        this.zplist = res.resdata;
        this.num = res.count;   
      });
    },
    setlx(ztid) {
      this.ztid = ztid;
      this.getList();
    },
    setqy(qyid) {
   
       this.qyid = qyid;
       this.getList();
    },
     setxs(xsid) {
   
       this.xsid = xsid;
       this.getList();
    },
    getZpqyList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/zpinfo/listZpqyAll";
      request.post(url, para).then((res) => {
        this.qylist = res.resdata;
      });
    },
    getZptypeList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/zpinfo/listZptypeAll";
      request.post(url, para).then((res) => {
        this.lxlist = res.resdata;
      });
    },
    getZpxsList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/zpinfo/listZpxsAll";
      request.post(url, para).then((res) => {
        this.xslist = res.resdata;
      });
    },
 

  },

  
};
</script>


<style lang="less" scoped>
</style>

 
