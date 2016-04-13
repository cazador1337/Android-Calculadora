package com.example.joao.mycaculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText saida, last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saida = (EditText) findViewById(R.id.saida);
        last = (EditText) findViewById(R.id.last);
        last.setEnabled(false);
    }

    public void addDigite(View v){
        String s = v.getTag().toString();
        if(s.contains(".")){
            s = saida.getText().toString().contains(".") ? "" : s;
        }
        saida.append(s);
    }

    public void clear(View v){
        saida.setText("");
    }

    public void operation (View v){
        String p = v.getTag().toString();
        String number = saida.getText().toString();
        if(!number.isEmpty()){
            boolean flag = !last.getText().toString().isEmpty();
            if(flag){
                calcular();
            }else {
                last.append(String.format("%s %s ",number,p));
                saida.setText("");
            }
        }
    }

    public void result(View v){
        calcular();
    }

    private void calcular() {
        String s = last.getText().toString()+saida.getText().toString();
        double[] d;
        double r = 0;

        if (s.contains("*")){
            d = toDobule(s.split("\\*"));
            r = d[0] * d[1];
        }else if (s.contains("/")){
            d = toDobule(s.split("/"));
            r = d[0] / d[1];
        }else if (s.contains("+")){
            d = toDobule(s.split("\\+"));
            r = d[0] + d[1];
        }else if (s.contains("-")){
            d = toDobule(s.split("-"));
            r = d[0] - d[1];
        }
        last.setText("");
        saida.setText(r + "");
    }

    public void calcular(View v){
        calcular();
    }

    public double[] toDobule(String... val){
        double[] d = new double[val.length];
        for (int i=0;i<d.length;i++){
            d[i] = Double.parseDouble(val[i]);
        }
        return d;
    }

}
