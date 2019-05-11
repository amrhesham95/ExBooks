package ex.devs.exbooks.Screens.signupScreen;

import android.content.Context;
import android.widget.Toast;

import ex.devs.exbooks.model.SignupAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupImpl implements SignupContract.SignupPresenterInterface {
    SignupContract.SignupViewInterface signupViewInterface;
    SignupAuth normalAuth;
    Matcher matcher ;
    Pattern pattern ;
    String regex;


    public SignupImpl(SignupContract.SignupViewInterface signupViewInterface) {
        this.signupViewInterface=signupViewInterface;
        normalAuth=new SignupAuth(this);
    }


    @Override
    public void signup(String email, String password,String phone) {

        if(email!=null && password!=null && phone != null){
            boolean checkPhone = checkPhone(phone);
            boolean checkEmail    = email.equals("")    || email.contains(" ");
            boolean checkPassword = password.equals("") || password.contains(" ");

            if(!checkEmail && !checkPassword){
                if(checkPhone) {
                    normalAuth.NormalSignup(email, password, phone);
                }else{
                    Toast.makeText(getContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getContext(), "Invalid email and password", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Context getContext() {
        return (Context) signupViewInterface;
    }


    @Override
    public boolean checkPhone(String phone) {
       // regex = "01\\d{9}";
        regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";

        // Create a Pattern object
        this.pattern = Pattern.compile(regex);
        // Now create matcher object.
        matcher = pattern.matcher(phone);
        return matcher.find();
        // && phone.length() == 11
    }



}
