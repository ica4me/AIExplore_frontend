package com.example.aiexplorer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import android.app.AlertDialog

class Profile : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val logoutButton = view.findViewById<Button>(R.id.logout)
        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
}

        val updateProfileButton = view.findViewById<Button>(R.id.profilupdate)
        updateProfileButton.setOnClickListener {
            startActivity(Intent(activity, UpdateProfile::class.java))
        }

        return view
    }



            private fun showLogoutConfirmationDialog() {
            AlertDialog.Builder(context)
                .setTitle("Logout Confirmation")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes") { dialog, which ->
                    startActivity(Intent(activity, LoginActivity::class.java))
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchUserData()
    }

    private fun fetchUserData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://20.255.62.2/aiexplore/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(UserApiService::class.java)

        apiService.getCurrentUser().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    response.body()?.let { user ->
                        view?.findViewById<TextView>(R.id.username)?.text = user.username
                        view?.findViewById<TextView>(R.id.email)?.text = user.email
                        view?.findViewById<TextView>(R.id.fullname)?.text = user.fullname
                        view?.findViewById<TextView>(R.id.caption)?.text = user.caption
                        view?.findViewById<TextView>(R.id.created_at)?.text = user.created_at
                    }
                } else {
                    Toast.makeText(context, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    interface UserApiService {
        @GET("current-user")
        fun getCurrentUser(): Call<User>
    }

    data class User(
        val username: String,
        val email: String,
        val fullname: String,
        val caption: String,
        val created_at: String
    )
}
