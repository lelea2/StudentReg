/**
 * Created by LeonZhang on 11/1/15.
 */
$(document).ready(function(){
    var n = 1;
    $("#flash").click(function(){
        $(".o_hFunction").slideToggle("fast");
            n = n * -1;
            if (n == -1){
                $(".rightSide").css("width","80%")
                $(".pageSzie").css("width","80%");
            }
            if (n == 1){
                $(".rightSide").css("width","100%")
                $(".pageSize").css("width","100%");
            }
    });


    $("#red").click(function(){
            $(".pageSize").hide("fast");
            $("#pageA").show("fast");
        }
    );
    $("#orange").click(function(){
        $(".pageSize").hide("fast");
        $("#pageB").show("fast");
    }
    );
    $("#green").click(function(){
        $(".pageSize").hide("fast");
        $("#pageC").show("fast");
    }
    );
});
