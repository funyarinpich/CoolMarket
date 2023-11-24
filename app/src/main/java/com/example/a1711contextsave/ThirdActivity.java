package com.example.a1711contextsave;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a1711contextsave.databinding.CoolmarketBinding;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoolmarketBinding binding = CoolmarketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+binding.phone.getText().toString()));
                startActivity(intent);
            }
        });
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"my@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Доставка продуктов");
                String s = "Добрый день! Вы заказали доставку из CoolMarket! В ваш заказ входит:\n";
                for (int i = 0; i < binding.LL.getChildCount(); i++) {
                    CheckBox cb =(CheckBox) binding.LL.getChildAt(i);
                    if(cb.isChecked()){
                        s+=cb.getText()+";\n";
                    }
                    cb =(CheckBox) binding.LL2.getChildAt(i);
                    if(cb.isChecked()){
                        s+=cb.getText()+";\n";
                    }
                }
                s+="Выбранный способ получения: ";
                int i = binding.Way.getCheckedRadioButtonId();
                s += i == 1 ? "Доставка" : "Самовывоз";
                EditText comment = findViewById(R.id.Comment);
                if (!comment.getText().toString().isEmpty()) s+="\nКомментарий:\n"+comment.getText().toString();
                intent.putExtra(Intent.EXTRA_TEXT,s);
                startActivity(intent);

            }
        });
    }

}
