const vm = Vue.createApp({
    data() {
        return {
            // 定义一个归档数据接受的容器--List
            blogs: [],
            // 定义一个归档数据接受的容器--Map
            blogs2:{}
        }
    },
    mounted() {
        // 执行查询归档数据
        this.loadBlogs()
        // 使用Map的方式
        // this.loadBlogs2()
    },

    methods: {
        // 异步查询归档接口数据---List方式
        loadBlogs() {
            axios.get("/archives/load2").then(res => {
                const {data: blogs, status} = res;
                if (status == 200) {
                    this.blogs = blogs;
                }
            })
        },

        // 异步查询归档接口数据---Map方式
        loadBlogs2() {
            axios.get("/archives/load").then(res => {
                const {data: blogs, status} = res;
                if (status == 200) {
                    this.blogs2 = blogs;
                }
            })
        }
    }
}).mount("#app");
