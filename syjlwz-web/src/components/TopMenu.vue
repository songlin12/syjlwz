<template>
  <div>
   <header class="header">
	<div class="container">
		<div class="navbar-header pull-left" style="290px;">
			<a href="#" class="navbar-brand">
        <img :src="logoPng" alt="设计作品交流网站" style="width:220px;height:60px;"  />
        
			</a>
			<button class="navbar-toggle" data-target=".navbar-collapse"
				data-toggle="collapse" type="button">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
		<nav class="collapse navbar-collapse pull-left" style="margin-left:120px;">
		<ul class="nav navbar-nav topmenu">
			<li class="visible-xs">
				<a href="#"><i
					class="fa fa-search mrm"></i>搜索</a>
			</li>
	    <li class="">
				<a href="/index">首页 </a>
			</li>

		 
			<li class="">
				<a href="/zpinfoAll">全部作品 </a>
			</li>

		 
		</ul>

		</nav>
		<div class="pull-right user-nav clearfix">
			<ul class="nav nav-login"  v-if="!islogin" >
				<li>
					<a class="color-gray-one"
						href="/uinfoLogin">登录</a>
				</li>
				<li>
					<a href="/register">注册</a>
				</li>
			</ul>



			<ul class="nav nav-login" v-if="islogin">
				<li>
					 {{uname}}
				</li>
				<li>
					<a   href="/main" style="cursor: pointer;">个人中心</a> 
				</li>
				<li>
					<a  @click="exit" >退出</a>
				</li>

			</ul>

 
			<form class="navbar-form pull-right hz-search hidden-xs"  
				role="search" action="/zpinfoSearch">
				<div class="form-group" style="margin-top:10px;">
					<div id="search-left" class="pull-left search-left">      
              <div class="search-type pull-left">
            </div>
						<input class="form-control js-search" name="qname"
							placeholder="输入要查找的内容" autocomplete="off">
					</div>
			 
					<button class="button fa fa-search" type="submit"></button>
				</div>
			</form>
		</div>
	</div>
	</header>
  </div>
</template>

<script>
import store from '../store'
export default {
  name: "TopMenu",
  data() {
    return {
      logoPng: require("@/assets/images/logo.png"),
      islogin:false,
      uname:''
    };
  },
  mounted() {
    var user = sessionStorage.getItem("userinfo");
  
    if (user) {
      user = JSON.parse(user);
 
      this.islogin=true;
      this.uname = user.username || "";

	   
    }
  
  },
  methods: {
    exit: function () {
      var _this = this;
      this.$confirm("确认退出吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          sessionStorage.removeItem("userinfo");
		   sessionStorage.removeItem("utype");
          this.islogin = false;
          _this.$router.push("/index");
        })
        .catch(() => {});
    },
  },
};
</script>

<style>
@import "../assets/css/bootstrap.css";
@import "../assets/css/howzhi.css";
@import "../assets/css/common.css";
@import "../assets/css/main.css";
@import "../assets/font-awesome/css/font-awesome.min.css";
 
</style>