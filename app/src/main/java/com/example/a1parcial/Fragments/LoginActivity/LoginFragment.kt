package com.example.a1parcial.Fragments.LoginActivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.a1parcial.Activities.ArticulosActivity
import com.example.a1parcial.DataBase.DataBase
import com.example.a1parcial.DataBase.Login.UserInfoDao
import com.example.a1parcial.DataBase.Login.UserLoginDao
import com.example.a1parcial.R
import com.example.a1parcial.Tools.check_empty
import com.example.a1parcial.Tools.send_message
import com.wajahatkarim3.roomexplorer.RoomExplorer

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    lateinit var loginView : View

    private var db : DataBase? = null
    private var userloginDao : UserLoginDao? = null
    private var userinfoDao : UserInfoDao? = null

    lateinit var txt_TitleLogin : TextView
    lateinit var edt_User : EditText
    lateinit var edt_Password : EditText
    lateinit var btn_Ingresar : Button
    lateinit var btn_Registrar : Button
    lateinit var btn_IngresoGoogle : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginView = inflater.inflate(R.layout.fragment_login, container, false)

        txt_TitleLogin = loginView.findViewById(R.id.txt_TitleLogin)
        edt_User = loginView.findViewById(R.id.edt_User)
        edt_Password = loginView.findViewById(R.id.edt_Password)
        btn_Ingresar = loginView.findViewById(R.id.btn_Ingresar)
        btn_Registrar = loginView.findViewById(R.id.btn_Registrar)
        btn_IngresoGoogle = loginView.findViewById(R.id.btn_IngresoGoogle)

        return loginView
    }

    override fun onStart() {
        super.onStart()
        db = DataBase.getDataBase(loginView.context)
        userloginDao = db?.userLoginDao()
        userinfoDao = db?.userInfoDao()

        btn_Registrar.setOnClickListener()
        {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            loginView.findNavController().navigate(action)
        }

        btn_Ingresar.setOnClickListener()
        {
            var flag = check_empty(this.loginView, "el usuario", edt_User)
            if (!flag)
                flag = check_empty(this.loginView, "la contraseña", edt_Password)
            if (!flag)
            {
                val compare_password = userloginDao?.loginUser(edt_User.text.toString())
                if(edt_Password.text.toString() == compare_password)
                {
                    activity?.startActivity(Intent(activity, ArticulosActivity::class.java))
                    activity?.finish()
                }
                else
                    send_message(this.loginView, "Los datos no coinciden")
            }
        }

        btn_IngresoGoogle.setOnClickListener()
        {
            RoomExplorer.show(context, DataBase::class.java, "DataBase")
        }
    }

}
