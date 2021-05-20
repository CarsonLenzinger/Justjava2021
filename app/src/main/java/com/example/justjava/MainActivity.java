/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match the package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;

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
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity","Has whipped cream: " + hasWhippedCream);


        // Figure out if user wants chocolate
        CheckBox chocolate = findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();
        Log.v("MainActivity","Has chocolate: " + hasChocolate);


        // Get text from EditText to Html
        EditText nameField = findViewById(R.id.user_input_name_view);
        String name = nameField.getText().toString();
        Log.v("MainActivity","Name: "+ name);


        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage =  createOrderSummary(price, hasWhippedCream, hasChocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: \"calenzinger@icloud.com\"?subject="+ Uri.encode("Just Java order for "+ name) + "&body=" + Uri.encode(priceMessage))); // only email apps should handle this
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }

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

        //adds 1$ if they want whipped cream
        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }
        //adds 2$ if they want chocolate
        if (hasChocolate){
            basePrice = basePrice +2;
        }
        //calculate the total order price by multiplying the quantity
        return quantity * basePrice;
    }



    /**
     * Create a summary of our order
     * @param name     of the customer
     * @param price    the total price
     * @param addWhippedCream is whether or not the user wants Whipped Cream Topping
     * @param chocolate chocolate
     * @return returns
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
    @SuppressLint("SetTextI18n")
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(message);

    }

}