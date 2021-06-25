log功能
测试功能
顺序发送功能


HttpHelper
    .post() / .getServer().login(new LoginBean())                               Request
    .addPar(key-value)
    .addPar(key-value)
    .addJson(new LoginBean())


    .next(new NextWork<T>(){
        Observer next(T t){
            return HttpHelper.post().addPar(key-T).observe(new Observer(){
                void onSuccess(){

                }

                void onFail(){

                }
            })
        }
    })
    .next(new NextWork(){
       Observer next(T t){
            return HttpHelper.post().addPar(key-T).observe(new Observer(){
                void onSuccess(){

                }

                void onFail(){

                }
            })
        }
    })


    .observe(new Observer(){
        void onSuccess(){

        }

        void onFail(){

        }
    });