var vue = new Vue({
    el: "#app",
    data: {
        blogCategories: [],
        cindex: -1,
        //  关于博客内容的返回和分页信息
        markflag: false,
        cid: 0,// 代表查询全部
        blogList: [],
        pageNo: 1,
        total: 0,
        pages: 0,
        loadStat:0,
        pageSize: 12
    },

    created: function () {
        this.cid = document.getElementById("cid").value;

        // 用ajax异步执行获取博客分类内容
        this.loadCategory();
        // 用ajax异步执行获取博客内容
        this.loadBlogContent();
    },

    mounted: function () {
        window.onscroll = this.throttle(() => {
            this.scrollBottom();
        }, 1000) //滑动则触发事件,2000毫秒响应一次
    },

    methods: {
        // 滚动分页
        scrollBottom() {
            //距离底部200px时则开始触发函数
            var bottomOfWindow = document.documentElement.scrollHeight - document.documentElement.scrollTop - window.innerHeight <= 20
            console.log(document.documentElement.scrollHeight - document.documentElement.scrollTop-window.innerHeight );
            //如果此时到了滑动底部&&底部显示正在加载&&请求过的总数据小于后台总数据
            if (bottomOfWindow && this.loadStat == 1) {
                this.loadMore();
            }
        },

        throttle(fn, delay) {
            let timer = null
            let firstTime = true
            console.log('!!1')
            return function (...args) {
                if (firstTime) {
                    // 第一次加载
                    fn.apply(this, args)
                    return (firstTime = false)
                }
                if (timer) {
                    // 定时器正在执行中，跳过
                    return
                }
                timer = setTimeout(() => {
                    clearTimeout(timer)
                    timer = null
                    fn.apply(this, args)
                }, delay)
            }
        },

        // 分类查询
        loadCategory: function () {
            var that = this;
            axios.get("/api/blogcategory/load").then(function (res) {
                that.blogCategories = res.data;//6

                var zindex = -1;
                for(var i=0;i<that.blogCategories.length;i++){
                    if(that.blogCategories[i].id == that.cid){
                        zindex = i;
                        break;
                    }
                }
                that.cindex = zindex;

            });
        },

        // 根据分类查询博客数据
        searchByCid: function (cid, index) {
            //this.cid = this.blogCategories[index].id;
            this.cid = cid;
            this.cindex = index;
            // 为什么要清空呢？因为每次点击是一次全新的搜索，必须先清空，把分页置到第一页即可
            this.blogList = [];
            this.pageNo = 1;
            this.loadBlogContent();
        },

        // 加载更多
        loadMore: function () {
            if (this.pageNo == this.pages) {
                this.loadStat = 2;
                return;
            }

            this.markflag = (this.pageNo == this.pages - 1);
            this.pageNo++;
            this.loadBlogContent();
        },

        // 查询博客内容
        loadBlogContent: function () {
            var that = this;
            // 1:获取 vue中分页信息 ,为什么定义vue的data，方便你后续去控制和下一页
            var pageNo = that.pageNo;//1
            var pageSize = that.pageSize;//10
            var cid = that.cid;//2
            axios.get("/api/blog/load?pageNo=" + pageNo + "&pageSize=" + pageSize + "&cid="+cid).then(function (res) {
                // 1: 获取后台返回的分页对象数据
                var blogPage = res.data;
                // 2：把分页数据中的数据获取到赋值给vue中data的blogList数据域中
                that.blogList = that.blogList.concat(blogPage.records);
                that.total = blogPage.total;
                that.pages = blogPage.pages;
                that.loadStat = 1;
            });
        }
    }
})