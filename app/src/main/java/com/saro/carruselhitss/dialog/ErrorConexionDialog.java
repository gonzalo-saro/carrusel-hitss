package com.saro.carruselhitss.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.saro.carruselhitss.activity.MainActivity;

public class ErrorConexionDialog extends DialogFragment
{
    private Intent intent;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Error de conexión")
                .setMessage("No se ha podido conectar con el servidor. Comprueba tu conexión a internet y vuelve a intentarlo.")
                .setNeutralButton("Reintentar", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        intent = new Intent(getActivity(), MainActivity.class);
                        if (getActivity() != null)
                        {
                            getActivity().startActivity(intent);
                            getActivity().finish();
                        } else
                        {
                            finalizaApp();
                        }
                    }
                })
                .setPositiveButton("Cerrar", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (getActivity() != null)
                        {
                            getActivity().finish();
                        }
                        finalizaApp();
                    }
                });
        setCancelable(false);
        return builder.create();
    }

    private void finalizaApp()
    {
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
