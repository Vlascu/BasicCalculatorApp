package com.example.widgetsapp;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
public class Calculator {
    private double result = 0.0;

    public void setResult(double number)
    {
        result=number;
    }
    public void addition(double number) {
        result += number;
    }

    public void subtract(double number) {
        result -= number;
    }

    public void division(double number) {
        result /= number;
    }

    public void multiply(double number) {
        result *= number;
    }

    public double getResult() {
        return result;
    }


}
