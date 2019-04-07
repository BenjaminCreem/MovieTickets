public class UtilityMethods {



    public String formatTime(String t){

        String hour = t.substring(0, 2);
        String minutes = t.substring(2, 4);
        String aorp = "AM";
        if(hour.charAt(0) == '0'){
            if(hour.charAt(1) == '0'){ //12 am
                hour = "12";
            }
            else{
                hour = hour.substring(1, 2);
            }
        }
        else if(hour.equals("11")){
            return hour + ":" + minutes + aorp;
        }
        else if(hour == "12"){
            return hour + ":" + minutes + aorp;
        }
        else{
            int convTime = Integer.parseInt(hour);
            if (convTime >= 12){
                aorp = "PM";
            }
            convTime = convTime - 12;
            if(convTime == 0){
                convTime = 12;
            }
            hour = convTime + "";
        }
        return hour + ":" + minutes + aorp;
    }
}
