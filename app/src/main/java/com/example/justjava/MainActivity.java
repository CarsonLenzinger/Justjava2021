/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match the package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     *Declare Global Variables Here
     */
    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the Increment/Plus button is clicked.
     */
    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    /**
     * This method is called when the Decrement/Minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity>0) {
            quantity--;
            display(quantity);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Figure out if user wants whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity","Has whipped cream: " + hasWhippedCream);


        // Figure out if user wants chocolate
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();
        Log.v("MainActivity","Has chocolate: " + hasChocolate);


        // Get text from EditText to Html
        EditText nameField = (EditText) findViewById(R.id.user_input_name_view);
        String name = nameField.getText().toString();
        Log.v("MainActivity","Name: "+ name);




        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage =  createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        displayMessage(priceMessage);
        
    }


    /**
     * Calculates the price of the order
     *
     * @param hasWhippedCream is whether or not the user wants whipped cream
     * @param hasChocolate is whether or not the user wants chocolate
     * @return total price
     */

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        int basePrice = 5;

        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (hasChocolate){
            basePrice = basePrice +2;
        } else {
            // do nothing
        }

        return quantity * basePrice;
    }



    /**
     * Create a summary of our order
     * @param name     of the customer
     * @param price    the total price
     * @param addWhippedCream is whether or not the user wants Whipped Cream Topping
     * @param chocolate
     * @return
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean chocolate, String name) {
        String priceMessage = "Name: "+ name;
        priceMessage += "\nThank you for ordering " + quantity + " Coffees!";
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + chocolate;//I used the escape key \n to put info on a new line
        priceMessage += "\nAmount Due: $" + price; //I used the escape key \n to put info on a new line
        priceMessage += "\n\nYour order will be right up!"; //Double \n escape key for w line separation
        return priceMessage;

    }




    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);

    }

}