import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import wanjiaList from '../pages/wanjia/list'
import wanjiaDetail from '../pages/wanjia/detail'
import wanjiaAdd from '../pages/wanjia/add'
import changdileixingList from '../pages/changdileixing/list'
import changdileixingDetail from '../pages/changdileixing/detail'
import changdileixingAdd from '../pages/changdileixing/add'
import changdixinxiList from '../pages/changdixinxi/list'
import changdixinxiDetail from '../pages/changdixinxi/detail'
import changdixinxiAdd from '../pages/changdixinxi/add'
import changdiyuyueList from '../pages/changdiyuyue/list'
import changdiyuyueDetail from '../pages/changdiyuyue/detail'
import changdiyuyueAdd from '../pages/changdiyuyue/add'
import bisaileixingList from '../pages/bisaileixing/list'
import bisaileixingDetail from '../pages/bisaileixing/detail'
import bisaileixingAdd from '../pages/bisaileixing/add'
import bisaixinxiList from '../pages/bisaixinxi/list'
import bisaixinxiDetail from '../pages/bisaixinxi/detail'
import bisaixinxiAdd from '../pages/bisaixinxi/add'
import zhuangbeifenleiList from '../pages/zhuangbeifenlei/list'
import zhuangbeifenleiDetail from '../pages/zhuangbeifenlei/detail'
import zhuangbeifenleiAdd from '../pages/zhuangbeifenlei/add'
import zhuangbeixinxiList from '../pages/zhuangbeixinxi/list'
import zhuangbeixinxiDetail from '../pages/zhuangbeixinxi/detail'
import zhuangbeixinxiAdd from '../pages/zhuangbeixinxi/add'
import baomingbisaiList from '../pages/baomingbisai/list'
import baomingbisaiDetail from '../pages/baomingbisai/detail'
import baomingbisaiAdd from '../pages/baomingbisai/add'
import bisaifenzuList from '../pages/bisaifenzu/list'
import bisaifenzuDetail from '../pages/bisaifenzu/detail'
import bisaifenzuAdd from '../pages/bisaifenzu/add'
import duizhananpaiList from '../pages/duizhananpai/list'
import duizhananpaiDetail from '../pages/duizhananpai/detail'
import duizhananpaiAdd from '../pages/duizhananpai/add'
import bisaichengjiList from '../pages/bisaichengji/list'
import bisaichengjiDetail from '../pages/bisaichengji/detail'
import bisaichengjiAdd from '../pages/bisaichengji/add'
import zhuangbeifenpeiList from '../pages/zhuangbeifenpei/list'
import zhuangbeifenpeiDetail from '../pages/zhuangbeifenpei/detail'
import zhuangbeifenpeiAdd from '../pages/zhuangbeifenpei/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import popupremindList from '../pages/popupremind/list'
import popupremindDetail from '../pages/popupremind/detail'
import popupremindAdd from '../pages/popupremind/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'
import discusschangdixinxiList from '../pages/discusschangdixinxi/list'
import discusschangdixinxiDetail from '../pages/discusschangdixinxi/detail'
import discusschangdixinxiAdd from '../pages/discusschangdixinxi/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'wanjia',
					component: wanjiaList
				},
				{
					path: 'wanjiaDetail',
					component: wanjiaDetail
				},
				{
					path: 'wanjiaAdd',
					component: wanjiaAdd
				},
				{
					path: 'changdileixing',
					component: changdileixingList
				},
				{
					path: 'changdileixingDetail',
					component: changdileixingDetail
				},
				{
					path: 'changdileixingAdd',
					component: changdileixingAdd
				},
				{
					path: 'changdixinxi',
					component: changdixinxiList
				},
				{
					path: 'changdixinxiDetail',
					component: changdixinxiDetail
				},
				{
					path: 'changdixinxiAdd',
					component: changdixinxiAdd
				},
				{
					path: 'changdiyuyue',
					component: changdiyuyueList
				},
				{
					path: 'changdiyuyueDetail',
					component: changdiyuyueDetail
				},
				{
					path: 'changdiyuyueAdd',
					component: changdiyuyueAdd
				},
				{
					path: 'bisaileixing',
					component: bisaileixingList
				},
				{
					path: 'bisaileixingDetail',
					component: bisaileixingDetail
				},
				{
					path: 'bisaileixingAdd',
					component: bisaileixingAdd
				},
				{
					path: 'bisaixinxi',
					component: bisaixinxiList
				},
				{
					path: 'bisaixinxiDetail',
					component: bisaixinxiDetail
				},
				{
					path: 'bisaixinxiAdd',
					component: bisaixinxiAdd
				},
				{
					path: 'zhuangbeifenlei',
					component: zhuangbeifenleiList
				},
				{
					path: 'zhuangbeifenleiDetail',
					component: zhuangbeifenleiDetail
				},
				{
					path: 'zhuangbeifenleiAdd',
					component: zhuangbeifenleiAdd
				},
				{
					path: 'zhuangbeixinxi',
					component: zhuangbeixinxiList
				},
				{
					path: 'zhuangbeixinxiDetail',
					component: zhuangbeixinxiDetail
				},
				{
					path: 'zhuangbeixinxiAdd',
					component: zhuangbeixinxiAdd
				},
				{
					path: 'baomingbisai',
					component: baomingbisaiList
				},
				{
					path: 'baomingbisaiDetail',
					component: baomingbisaiDetail
				},
				{
					path: 'baomingbisaiAdd',
					component: baomingbisaiAdd
				},
				{
					path: 'bisaifenzu',
					component: bisaifenzuList
				},
				{
					path: 'bisaifenzuDetail',
					component: bisaifenzuDetail
				},
				{
					path: 'bisaifenzuAdd',
					component: bisaifenzuAdd
				},
				{
					path: 'duizhananpai',
					component: duizhananpaiList
				},
				{
					path: 'duizhananpaiDetail',
					component: duizhananpaiDetail
				},
				{
					path: 'duizhananpaiAdd',
					component: duizhananpaiAdd
				},
				{
					path: 'bisaichengji',
					component: bisaichengjiList
				},
				{
					path: 'bisaichengjiDetail',
					component: bisaichengjiDetail
				},
				{
					path: 'bisaichengjiAdd',
					component: bisaichengjiAdd
				},
				{
					path: 'zhuangbeifenpei',
					component: zhuangbeifenpeiList
				},
				{
					path: 'zhuangbeifenpeiDetail',
					component: zhuangbeifenpeiDetail
				},
				{
					path: 'zhuangbeifenpeiAdd',
					component: zhuangbeifenpeiAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'popupremind',
					component: popupremindList
				},
				{
					path: 'popupremindDetail',
					component: popupremindDetail
				},
				{
					path: 'popupremindAdd',
					component: popupremindAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
				{
					path: 'discusschangdixinxi',
					component: discusschangdixinxiList
				},
				{
					path: 'discusschangdixinxiDetail',
					component: discusschangdixinxiDetail
				},
				{
					path: 'discusschangdixinxiAdd',
					component: discusschangdixinxiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
