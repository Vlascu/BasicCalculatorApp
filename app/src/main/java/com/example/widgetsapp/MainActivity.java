package com.example.widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.LongPredicate;


public class MainActivity extends AppCompatActivity {

    Calculator calc = new Calculator();
    TextView resultBox;
    String currentOperation = "";
    String currentText = "";
    String lastOp = "";
    String numberOfDigits = "";
    Queue<String> operationsOrder = new LinkedList<>();
    String currentNumber = "null";
    boolean firstNumber=true;

    public void getButtonNumber(Button num_button, TextView show_box) {
        num_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastOp.equals("=")) {
                    show_box.setText("");
                    lastOp = "";
                    calc.setResult(0);
                }
                if (currentNumber.equals("null")) {
                    currentNumber = "";
                }
                if (!num_button.getText().toString().equals("C"))
                {
                    currentNumber += num_button.getText().toString();
                    if (currentNumber.length() > 15) {
                        show_box.setText("");
                        show_box.setText("Number greater than 15 not allowed!");
                        lastOp="=";
                        currentNumber = "null";
                    } else {
                        currentText = show_box.getText().toString();
                        show_box.setText(currentText + num_button.getText().toString());
                    }
                }
                else
                {
                    String copyString =currentNumber;
                    currentNumber = copyString.substring(0,copyString.length()-1);
                    show_box.setText(currentText);
                }

            }
        });
    }

    public void getButtonOperation(Button operation_button, TextView show_box) {
        operation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence operation = operation_button.getText();
                String op = operation.toString();
                operationsOrder.add(op);
                if (!op.equals("=")) {
                    currentText = show_box.getText().toString();
                    show_box.setText(currentText + op);
                }
                if (currentNumber.equals("0") && operationsOrder.peek().equals("/")) {
                    show_box.setText("");
                    currentText = " ";
                    show_box.setText("Can't divide by zero!");
                    lastOp = "=";
                    operationsOrder.clear();
                } else {
                    if (currentNumber.equals("null")) {
                        lastOp = "";
                    } else {
                        if (calc.getResult() == 0 && firstNumber) {
                            calc.setResult(Double.parseDouble(currentNumber));
                            firstNumber=false;
                        } else {
                            currentOperation = operationsOrder.poll();
                            switch (currentOperation) {
                                case "+": {
                                    calc.addition(Double.parseDouble(currentNumber));
                                    break;
                                }
                                case "-": {
                                    calc.subtract(Double.parseDouble(currentNumber));
                                    break;
                                }
                                case "/": {
                                    calc.division(Double.parseDouble(currentNumber));
                                    break;
                                }
                                case "x": {
                                    calc.multiply(Double.parseDouble(currentNumber));
                                    break;
                                }
                            }
                            if (op.equals("=")) {
                                show_box.setText(String.valueOf(calc.getResult()));
                                currentText = " ";
                                lastOp = "=";
                                operationsOrder.remove();
                            }
                        }
                    }
                }
                currentNumber = "null";
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultBox = (TextView) findViewById(R.id.result);

        Button number1 = (Button) findViewById(R.id.num1);
        getButtonNumber(number1, resultBox);
        Button number2 = (Button) findViewById(R.id.num2);
        getButtonNumber(number2, resultBox);
        Button number3 = (Button) findViewById(R.id.num3);
        getButtonNumber(number3, resultBox);
        Button number4 = (Button) findViewById(R.id.num4);
        getButtonNumber(number4, resultBox);
        Button number5 = findViewById(R.id.num5);
        getButtonNumber(number5, resultBox);
        Button number6 = findViewById(R.id.num6);
        getButtonNumber(number6, resultBox);
        Button number7 = findViewById(R.id.num7);
        getButtonNumber(number7, resultBox);
        Button number8 = findViewById(R.id.num8);
        getButtonNumber(number8, resultBox);
        Button number9 = findViewById(R.id.num9);
        getButtonNumber(number9, resultBox);
        Button number0 = findViewById(R.id.num0);
        getButtonNumber(number0, resultBox);
        Button clearNumber = findViewById(R.id.clearBtn);
        getButtonNumber(clearNumber,resultBox);
        Button add = findViewById(R.id.add);
        getButtonOperation(add, resultBox);
        Button subtract = findViewById(R.id.subtract);
        getButtonOperation(subtract, resultBox);
        Button multiply = findViewById(R.id.multiply);
        getButtonOperation(multiply, resultBox);
        Button divide = findViewById(R.id.divide);
        getButtonOperation(divide, resultBox);
        Button equalButton = findViewById(R.id.equalButton);
        getButtonOperation(equalButton, resultBox);
    }
}