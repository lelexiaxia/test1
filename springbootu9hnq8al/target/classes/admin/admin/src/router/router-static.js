import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Board from '@/views/board'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import aboutus from '@/views/modules/aboutus/list'
	import zhuangbeifenlei from '@/views/modules/zhuangbeifenlei/list'
	import duizhananpai from '@/views/modules/duizhananpai/list'
	import wanjia from '@/views/modules/wanjia/list'
	import zhuangbeixinxi from '@/views/modules/zhuangbeixinxi/list'
	import baomingbisai from '@/views/modules/baomingbisai/list'
	import changdixinxi from '@/views/modules/changdixinxi/list'
	import discusschangdixinxi from '@/views/modules/discusschangdixinxi/list'
	import zhuangbeifenpei from '@/views/modules/zhuangbeifenpei/list'
	import changdileixing from '@/views/modules/changdileixing/list'
	import bisaixinxi from '@/views/modules/bisaixinxi/list'
	import popupremind from '@/views/modules/popupremind/list'
	import systemintro from '@/views/modules/systemintro/list'
	import bisaileixing from '@/views/modules/bisaileixing/list'
	import bisaifenzu from '@/views/modules/bisaifenzu/list'
	import bisaichengji from '@/views/modules/bisaichengji/list'
	import config from '@/views/modules/config/list'
	import changdiyuyue from '@/views/modules/changdiyuyue/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '公告资讯',
		component: news
	}
	,{
		path: '/aboutus',
		name: '关于我们',
		component: aboutus
	}
	,{
		path: '/zhuangbeifenlei',
		name: '装备分类',
		component: zhuangbeifenlei
	}
	,{
		path: '/duizhananpai',
		name: '对战安排',
		component: duizhananpai
	}
	,{
		path: '/wanjia',
		name: '玩家',
		component: wanjia
	}
	,{
		path: '/zhuangbeixinxi',
		name: '装备信息',
		component: zhuangbeixinxi
	}
	,{
		path: '/baomingbisai',
		name: '报名比赛',
		component: baomingbisai
	}
	,{
		path: '/changdixinxi',
		name: '场地信息',
		component: changdixinxi
	}
	,{
		path: '/discusschangdixinxi',
		name: '场地信息评论',
		component: discusschangdixinxi
	}
	,{
		path: '/zhuangbeifenpei',
		name: '装备分配',
		component: zhuangbeifenpei
	}
	,{
		path: '/changdileixing',
		name: '场地类型',
		component: changdileixing
	}
	,{
		path: '/bisaixinxi',
		name: '比赛信息',
		component: bisaixinxi
	}
	,{
		path: '/popupremind',
		name: '弹窗提醒',
		component: popupremind
	}
	,{
		path: '/systemintro',
		name: '系统简介',
		component: systemintro
	}
	,{
		path: '/bisaileixing',
		name: '比赛类型',
		component: bisaileixing
	}
	,{
		path: '/bisaifenzu',
		name: '比赛分组',
		component: bisaifenzu
	}
	,{
		path: '/bisaichengji',
		name: '比赛成绩',
		component: bisaichengji
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/changdiyuyue',
		name: '场地预约',
		component: changdiyuyue
	}
	,{
		path: '/newstype',
		name: '公告资讯分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/board',
		name: 'board',
		component: Board,
		meta: {icon:'', title:'board'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
