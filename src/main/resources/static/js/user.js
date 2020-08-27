let index = {
    init: function() {
        $("#btn-save").on("click",()=>{
            this.save();
        });
    },

    save:function(){
        // alert('user의 save 함수 호출됨');
        let data = {
            //id값을 통해 찾는다. 그 아이디 값이 들고 있는 값을 변수에 바인딩
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        // console.log(data);

        $.ajax().done().fail(); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    }
}

//호출되면서 바인드 되고 있음
index.init();