package com.example.a1parcial.Fragments.LoginActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.a1parcial.DataBase.DataBase
import com.example.a1parcial.DataBase.Login.UserInfoDao
import com.example.a1parcial.DataBase.Login.UserLoginDao
import com.example.a1parcial.Entities.Login.UserInfo
import com.example.a1parcial.Entities.Login.UserLogin
import com.example.a1parcial.R
import com.example.a1parcial.Tools.check_empty
import com.example.a1parcial.Tools.send_message

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    lateinit var registerView : View

    private var db : DataBase? = null
    private var userloginDao : UserLoginDao? = null
    private var userinfoDao : UserInfoDao? = null

    lateinit var edt_User : EditText
    lateinit var edt_Email : EditText
    lateinit var edt_Password : EditText
    lateinit var edt_Password2: EditText
    lateinit var btn_Registrarse : Button
    lateinit var btn_Regresar : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        registerView = inflater.inflate(R.layout.fragment_register, container, false)

        edt_User = registerView.findViewById(R.id.edt_User)
        edt_Email = registerView.findViewById(R.id.edt_Email)
        edt_Password = registerView.findViewById(R.id.edt_Password)
        edt_Password2 = registerView.findViewById(R.id.edt_Password2)
        btn_Registrarse = registerView.findViewById(R.id.btn_Registrar)
        btn_Regresar = registerView.findViewById(R.id.btn_Regresar)

        return registerView
    }

    override fun onStart() {
        super.onStart()
        db = DataBase.getDataBase(registerView.context)
        userloginDao = db?.userLoginDao()
        userinfoDao = db?.userInfoDao()

        btn_Regresar.setOnClickListener()
        {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            registerView.findNavController().navigate(action)
        }

        btn_Registrarse.setOnClickListener()
        {
            var flag = check_empty(this.registerView, "el usuario", edt_User)
            if (!flag)
                flag = check_empty(this.registerView, "el Email", edt_Email)
            if (!flag)
                flag = check_empty(this.registerView, "la contraseña", edt_Password)
            if (!flag)
                flag = check_empty(this.registerView, "la contraseña de confirmación", edt_Password2)
            if (!flag)
            {
                if (edt_Password.text.toString() == edt_Password2.text.toString())
                {
                    val newUserLogin = UserLogin(edt_User.text.toString(), edt_Password.text.toString())
                    userloginDao?.insertUserLogin(newUserLogin)
                    val userID = userloginDao?.getUserId(edt_User.text.toString())

                    val newUserInfo =
                        userID?.let { it1 -> UserInfo(it1, edt_User.text.toString(), edt_Email.text.toString()) }
                    userinfoDao?.insertUserInfo(newUserInfo)


                    send_message(this.registerView, "Cuenta creada satisfactoriamente")
                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    registerView.findNavController().navigate(action)
                }
                else
                    send_message(this.registerView, "No coinciden las contraseñas")
            }
        }

    }

}
