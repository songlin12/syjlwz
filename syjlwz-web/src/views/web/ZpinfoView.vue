<template>
  <div>
      	<section class="course-header-new">
		<div class="container">
			<!-- 面包屑 -->
			<div class="bread_crumb">


			</div>


			<div class="row hz-course-before clearfix">
				<div class=" col-md-5 col-xs-12" align="center">
	 
                <img  class="img-responsive" :src="'http://localhost:8088/syjlwz/'+zpinfo.fujian"   />


				</div>
				<div class="info col-md-7 col-xs-12 pull-right">
					<h1>
						{{zpinfo.name}}     
				                
					</h1>
		     <div class="score" style="color:#888888;font-size:14px;">
						上传人：{{uinfo.username}}               
					</div>
         

  
				</div>
			</div>

		</div>

		</section>

		<section class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="course-lessons flat" data-widget-cid="widget-1">
					<div class="hz-overview hzcourse-detail " >
					 
						<div class="editor-text ptl">
						 <img  class="img-responsive" :src="'http://localhost:8088/syjlwz/'+zpinfo.fujian"    />


						</div>

					</div>
					
											</div>

					</div>
					
					
					
					
					<div class="row">
				<div class="col-md-12">
					<div class="person-comment course-in flat">
						<h2 class="nopadding">
							评论
						</h2>
					
						<ul class=" comment-item-list" id="commentList">

					 
								<li id="item93830" v-for="item in plist" :key="item.id">
                                   
									<div class="userInfo">
										<p class="head">
											<a href="#">{{item.uinfoVO.username}}</a><span>{{item.plsj}}</span>
										</p>
										<div class="body">
											{{item.plnr}}
										</div>
	                                     <div v-if="item.plhf!=null && item.plhf!=''" class="body">
											回复：{{item.plhf}}
										</div>

									</div>
								</li>
						 







						</ul>
						
						
					 
  
                            <input name="gid" value="${course.id}" type="hidden"/>
							<div class="form-group">
								<label class="control-label hidden" for="comment">
									内容
								</label>
								<div class="controls ">
									<textarea id="lynr" name="plnr" v-model="formData.plnr" class="form-control question_title "  ></textarea>
									<div class="help-block"></div>

								</div>
							</div>


							<div class="clearfix">
								<button class="btn btn-primary pull-right" type="button" @click="fabu()">
									发布
								</button>
							</div>
							<div class="hz-login-editor"></div>
					 
					</div>


				</div>

			</div>
			
			
			
 



				</div>
				</section>




  </div>
</template>

<script>
import request, { base } from "../../../utils/http";
import store from "../../store";
export default {
  components: {},
  data() {
    return {
      id: 0,
      zpinfo:[],
      uinfo:[],
      plist: [],
     formData: {},  
    };
  },

  created() {
    this.getInfo();
    this.id = this.$route.query.id;
    store.commit("setState", 0);
  },
  methods: {
    showInfo() {
      this.formVisible = true;
    },
    getInfo() {
      let id = this.$route.query.id;
      let para = {};

      let url = base + "/zpinfo/get?id=" + id;
      request.post(url, para).then((res) => {
        this.zpinfo = res.resdata;
        this.plist = res.resdata.plist;
        this.uinfo = res.resdata.uinfoVO;
      });
    },
    fabu(){


      let plnr = this.formData.plnr;
  
      if (!plnr) {
        this.$message({
          message: "请输入评论内容",
          type: "error",
          offset: 320,
        });
        return false;
      }

      var user = sessionStorage.getItem("userinfo");
      if (user) {
        let url = base + "/plinfo/add";
        user = JSON.parse(user);
        let para = { zpid: this.zpinfo.id, uid: user.id,plnr:this.formData.plnr };
        request.post(url, para).then((res) => {
          let code = res.code;
          if (code == 200) {
              this.formData = {};
              this.formData.plnr = "";
              this.getInfo();
          }
        });
      } else {
        this.$router.push("/uinfoLogin");
      }
      
      
    }
  },

};
</script>


<style lang="less" scoped>
</style>

 
