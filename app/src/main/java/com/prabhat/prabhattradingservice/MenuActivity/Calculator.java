package com.prabhat.prabhattradingservice.MenuActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prabhat.prabhattradingservice.MainActivity;
import com.prabhat.prabhattradingservice.R;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public class Calculator extends AppCompatActivity {
ActionBar actionBar;
RecyclerView recyclerView;
    ImageView iv_back;

    private HashMap _$_findViewCache;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
      //  actionBarSetup();


         iv_back = (ImageView) findViewById(R.id.iv_back4);
         iv_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent);
                 finish();
             }
         });


         ((TextView)this._$_findCachedViewById(R.id.tvOne)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("1", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvTwo)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("2", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvThree)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("3", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvFour)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("4", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvFive)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("5", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvSix)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("6", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvSeven)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("7", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvEight)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("8", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvNine)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("9", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvZero)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("0", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvDot)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion(".", true);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvPlus)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("+", false);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvMinus)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("-", false);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvMul)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("*", false);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvDivide)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("/", false);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvOpen)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion("(", false);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvClose)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 Calculator.this.appendOnExpresstion(")", false);
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvClear)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 TextView var10000 = (TextView)Calculator.this._$_findCachedViewById(R.id.tvExpression);
                 Intrinsics.checkExpressionValueIsNotNull(var10000, "tvExpression");
                 var10000.setText((CharSequence)"");
                 var10000 = (TextView)Calculator.this._$_findCachedViewById(R.id.tvResult);
                 Intrinsics.checkExpressionValueIsNotNull(var10000, "tvResult");
                 var10000.setText((CharSequence)"");
             }
         }));
         ((ImageView)this._$_findCachedViewById(R.id.tvBack)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 TextView var10000 = (TextView)Calculator.this._$_findCachedViewById(R.id.tvExpression);
                 Intrinsics.checkExpressionValueIsNotNull(var10000, "tvExpression");
                 String string = var10000.getText().toString();
                 CharSequence var3 = (CharSequence)string;
                 if (var3.length() > 0) {
                     var10000 = (TextView)Calculator.this._$_findCachedViewById(R.id.tvExpression);
                     Intrinsics.checkExpressionValueIsNotNull(var10000, "tvExpression");
                     byte var4 = 0;
                     int var5 = string.length() - 1;
                     if (string == null) {
                         throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                     }

                     String var10001 = string.substring(var4, var5);
                     Intrinsics.checkExpressionValueIsNotNull(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                     var10000.setText((CharSequence)var10001);
                 }

                 var10000 = (TextView)Calculator.this._$_findCachedViewById(R.id.tvResult);
                 Intrinsics.checkExpressionValueIsNotNull(var10000, "tvResult");
                 var10000.setText((CharSequence)"");
             }
         }));
         ((TextView)this._$_findCachedViewById(R.id.tvEquals)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
             public final void onClick(View it) {
                 try {
                     TextView var10002 = (TextView)Calculator.this._$_findCachedViewById(R.id.tvExpression);
                     Intrinsics.checkExpressionValueIsNotNull(var10002, "tvExpression");
                    Expression expression = (new ExpressionBuilder(var10002.getText().toString())).build();
                     double result = expression.evaluate();




                     long longResult = (long)result;
                     TextView var10000;
                     if (result == (double)longResult) {
                         var10000 = (TextView)Calculator.this._$_findCachedViewById(R.id.tvResult);
                         Intrinsics.checkExpressionValueIsNotNull(var10000, "tvResult");
                         var10000.setText((CharSequence)String.valueOf(longResult));
                     } else {
                         var10000 = (TextView)Calculator.this._$_findCachedViewById(R.id.tvResult);
                         Intrinsics.checkExpressionValueIsNotNull(var10000, "tvResult");
                         var10000.setText((CharSequence)String.valueOf(result));
                     }
                 } catch (Exception var7) {
                     Log.d("Exception", " message : " + var7.getMessage());
                 }

             }
         }));

     }

    public final void appendOnExpresstion(@NotNull String string, boolean canClear) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        TextView var10000 = (TextView)this._$_findCachedViewById(R.id.tvResult);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "tvResult");
        CharSequence var4 = var10000.getText();
        Intrinsics.checkExpressionValueIsNotNull(var4, "tvResult.text");
        CharSequence var3 = var4;
        if (var3.length() > 0) {
            var10000 = (TextView)this._$_findCachedViewById(R.id.tvExpression);
            Intrinsics.checkExpressionValueIsNotNull(var10000, "tvExpression");
            var10000.setText((CharSequence)"");
        }

        if (canClear) {
            var10000 = (TextView)this._$_findCachedViewById(R.id.tvResult);
            Intrinsics.checkExpressionValueIsNotNull(var10000, "tvResult");
            var10000.setText((CharSequence)"");
            ((TextView)this._$_findCachedViewById(R.id.tvExpression)).append((CharSequence)string);
        } else {
            var10000 = (TextView)this._$_findCachedViewById(R.id.tvExpression);
            TextView var10001 = (TextView)this._$_findCachedViewById(R.id.tvResult);
            Intrinsics.checkExpressionValueIsNotNull(var10001, "tvResult");
            var10000.append(var10001.getText());
            ((TextView)this._$_findCachedViewById(R.id.tvExpression)).append((CharSequence)string);
            var10000 = (TextView)this._$_findCachedViewById(R.id.tvResult);
            Intrinsics.checkExpressionValueIsNotNull(var10000, "tvResult");
            var10000.setText((CharSequence)"");
        }

    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }

   // Action bar change tittle
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Calculator");
        }
    }
// set home check
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      /*  if (item.getItemId()==android.R.id.home){
            finish();

        }*/
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(Calculator.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Calculator.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }



}