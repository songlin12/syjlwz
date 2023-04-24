<template>
    <div id="nongjitong_home">
        <div>
            <TopMenu/>
        </div>
        <router-view/>
    </div>
</template>

<style>

</style>

<script>
    import TopMenu from "../components/TopMenu";

    export default {
        name:"IndexLayout",
        components:{
            TopMenu,
        },
        data() {
            return {
               
            }
        },
    }





</script>
<style>
 @import "../../assets/css/idangerous.swiper.css";
@import "../../assets/css/cmspc.min.css";
</style>