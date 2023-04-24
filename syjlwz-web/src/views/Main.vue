<template>
    <el-config-provider :locale="locale">
        <div>
            <Header/>
        </div>
        <div style="display: flex;margin-top: 90px;">
            <LeftMenu/>
            <router-view/>
        </div>
    </el-config-provider>
</template>

<style>

</style>

<script>
    import Header from "../components/Header";
    import LeftMenu from "../components/LeftMenu";
    import { ElConfigProvider } from 'element-plus';
    import zhCn from 'element-plus/lib/locale/lang/zh-cn'
    export default {
        name:"MainLayout",
        components:{
            Header,
            LeftMenu,
            [ElConfigProvider.name]: ElConfigProvider
        },
        data() {
            return {
                locale: zhCn,
            }
        },
    }





</script>
