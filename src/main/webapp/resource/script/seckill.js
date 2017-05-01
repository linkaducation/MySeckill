//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)

var seckill = {

    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckill_id) {
            return '/seckill/' + seckill_id + '/exposer';
        },
        execution: function (seckill_id, md5) {
            return '/seckill/' + seckill_id + '/' + md5 + '/execution';
        }
    },

    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;//直接判断对象会看对象是否为空,空就是undefine就是false; isNaN 非数字返回true
        } else {
            return false;
        }
    },

    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录,计时交互
            //规划我们的交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            var start_time = params['start_time'];
            var end_time = params['end_time'];
            var seckill_id = params['seckill_id'];
            //验证手机号
            if (!seckill.validatePhone(killPhone)) {
                //绑定手机 控制输出
                $('#killPhoneModal').removeClass("fade").addClass("show");
                $('#killPhoneBtn').click(function () {
                    var inputphone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputphone)){
                        $.cookie('killPhone',inputphone,{expires:7,path:'/seckill'});
                        //刷新页面
                        window.location.reload();
                    }else {
                        $('#killPhoneMessage').hide().html('<label class="lable label-danger">手机号错误!</label>').show(300);
                    }
                });
            }
        }
    }









    

    // handlerSeckill: function (seckill_id, node) {
    //     //获取秒杀地址,控制显示器,执行秒杀
    //     node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
    //
    //     $.post(seckill.URL.exposer(seckill_id), {}, function (result) {
    //         //在回调函数种执行交互流程
    //         if (result && result['success']) {
    //             var exposer = result['data'];
    //             if (exposer['exposed']) {
    //                 //开启秒杀
    //                 //获取秒杀地址
    //                 var md5 = exposer['md5'];
    //                 var killUrl = seckill.URL.execution(seckill_id, md5);
    //                 console.log("killUrl: " + killUrl);
    //                 //绑定一次点击事件
    //                 $('#killBtn').one('click', function () {
    //                     //执行秒杀请求
    //                     //1.先禁用按钮
    //                     $(this).addClass('disabled');//,<-$(this)===('#killBtn')->
    //                     //2.发送秒杀请求执行秒杀
    //                     $.post(killUrl, {}, function (result) {
    //                         if (result && result['success']) {
    //                             var killResult = result['data'];
    //                             var state = killResult['state'];
    //                             var stateInfo = killResult['stateInfo'];
    //                             //显示秒杀结果
    //                             node.html('<span class="label label-success">' + stateInfo + '</span>');
    //                         }
    //                     });
    //                 });
    //                 node.show();
    //             } else {
    //                 //未开启秒杀(浏览器计时偏差)
    //                 var now = exposer['now'];
    //                 var start = exposer['start'];
    //                 var end = exposer['end'];
    //                 seckill.countDown(seckill_id, now, start, end);
    //             }
    //         } else {
    //             console.log('result: ' + result);
    //         }
    //     });
    //
    // },
    //
    // countDown: function (seckill_id, now_time, start_time, end_time) {
    //         console.log(seckill_id + '_' + now_time + '_' + start_time + '_' + end_time);
    //     var seckillBox = $('#seckill-box');
    //     if (now_time > end_time) {
    //         //秒杀结束
    //         seckillBox.html('秒杀结束!');
    //     } else if (now_time < start_time) {
    //         //秒杀未开始,计时事件绑定
    //         var kill_time = new Date(start_time + 1000);//todo 防止时间偏移
    //         seckillBox.countdown(kill_time, function (event) {
    //             //时间格式
    //             var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒 ');
    //             seckillBox.html(format);
    //         }).on('finish.countdown', function () {
    //             //时间完成后回调事件
    //             //获取秒杀地址,控制现实逻辑,执行秒杀
    //             console.log('______fininsh.countdown');
    //             seckill.handlerSeckill(seckill_id, seckillBox);
    //         });
    //     } else {
    //         //秒杀开始
    //         seckill.handlerSeckill(seckill_id, seckillBox);
    //     }
    // }

}