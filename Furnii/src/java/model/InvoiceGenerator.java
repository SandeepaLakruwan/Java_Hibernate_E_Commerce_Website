/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Cart;
import entity.Product;
import java.util.Date;
import java.util.List;

public class InvoiceGenerator {

    // Method to generate HTML invoice
    public static String generateInvoiceHtml(String customerName, String customerAddress, String customerEmail, 
                                      String customerPhone, Date invoiceDate, int invoiceNumber, 
                                      List<Cart> products, double taxRate, 
                                      String paymentMethod, String transactionId, String paymentStatus) {

        // Start building the HTML content
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>")
                   .append("<html lang=\"en\">")
                   .append("<head>")
                   .append("<meta charset=\"UTF-8\">")
                   .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
                   .append("<title>Furnii - Invoice</title>")
                   .append("<!-- Bootstrap CSS -->")
                   .append("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">")
                   .append("<style>")
                   .append("body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; }")
                   .append(".invoice-box { max-width: 850px; margin: auto; padding: 30px; border: 1px solid #ddd; ")
                   .append("background-color: #fff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }")
                   .append(".invoice-header { display: flex; justify-content: space-between; margin-bottom: 20px; }")
                   .append(".invoice-header img { max-height: 70px; }")
                   .append(".invoice-header h1 { font-size: 32px; color: #333; margin-bottom: 0; }")
                   .append(".table th { background-color: #343a40; color: white; padding: 10px; }")
                   .append(".table td { padding: 10px; }")
                   .append(".table-bordered { border: 1px solid #343a40; }")
                   .append(".total-row { font-weight: bold; background-color: #f8f9fa; }")
                   .append(".footer { margin-top: 50px; text-align: center; color: #777; font-size: 14px; padding-top: 20px; border-top: 1px solid #ddd; }")
                   .append("</style>")
                   .append("</head>")
                   .append("<body>")
                   .append("<div class=\"invoice-box\">");

        // Header with company info and logo
        htmlBuilder.append("<div class=\"invoice-header\">")
                   .append("<div><h1>Furnii - Invoice</h1></div>")
                   .append("</div>");

        // Company and Customer Information
        htmlBuilder.append("<div class=\"row mb-4\">")
                   .append("<div class=\"col-md-6\">")
                   .append("<h4>Furnii Furniture Store</h4>")
                   .append("<p>123 Furniture Street<br>Haverlock Road, Colombo 05<br>Email: support@furnii.com<br>Phone: +94 777 1212555</p>")
                   .append("</div>")
                   .append("<div class=\"col-md-6 text-end\">")
                   .append("<h4>Invoice To:</h4>")
                   .append("<p>").append(customerName).append("<br>")
                   .append(customerAddress).append("<br>")
                   .append("Email: ").append(customerEmail).append("<br>")
                   .append("Phone: ").append(customerPhone).append("</p>")
                   .append("</div>")
                   .append("</div>");

        // Invoice Details
        htmlBuilder.append("<div class=\"row mb-4\">")
                   .append("<div class=\"col-md-6\">")
                   .append("<h5>Invoice Date:</h5>")
                   .append("<span>").append(invoiceDate).append("</span>")
                   .append("</div>")
                   .append("<div class=\"col-md-6 text-end\">")
                   .append("<h5>Invoice Number:</h5>")
                   .append("<span>#").append(invoiceNumber).append("</span>")
                   .append("</div>")
                   .append("</div>");

        // Products Table
        htmlBuilder.append("<div class=\"row\"><div class=\"col-md-12\">")
                   .append("<table class=\"table table-bordered table-striped\">")
                   .append("<thead><tr><th>Item</th><th>Quantity</th><th>Unit Price</th><th>Total</th></tr></thead>")
                   .append("<tbody>");

        double shippingFee = 0.0;
        double subtotal = 0.0;
        for (Cart product : products) {
            double total = product.getProduct().getPrice() * product.getQty();
            shippingFee = product.getProduct().getDelivery_fee();
            subtotal += total;
            htmlBuilder.append("<tr>")
                       .append("<td>").append(product.getProduct().getTitle()).append("</td>")
                       .append("<td>").append(product.getQty()).append("</td>")
                       .append("<td>Rs. ").append(String.format("%.2f", product.getProduct().getPrice())).append("</td>")
                       .append("<td>Rs. ").append(String.format("%.2f", total)).append("</td>")
                       .append("</tr>");
        }

        // Tax, Shipping, and Total
        double taxAmount = subtotal * taxRate;
        double totalAmount = subtotal + taxAmount + shippingFee;

        htmlBuilder.append("<tr class=\"total-row\"><td colspan=\"3\" class=\"text-end\"><strong>Subtotal:</strong></td>")
                   .append("<td>Rs. ").append(String.format("%.2f", subtotal)).append("</td></tr>")
                   .append("<tr class=\"total-row\"><td colspan=\"3\" class=\"text-end\"><strong>Tax (").append(taxRate * 100).append("%):</strong></td>")
                   .append("<td>Rs. ").append(String.format("%.2f", taxAmount)).append("</td></tr>")
                   .append("<tr class=\"total-row\"><td colspan=\"3\" class=\"text-end\"><strong>Shipping:</strong></td>")
                   .append("<td>Rs. ").append(String.format("%.2f", shippingFee)).append("</td></tr>")
                   .append("<tr class=\"total-row\"><td colspan=\"3\" class=\"text-end\"><strong>Total:</strong></td>")
                   .append("<td><strong>Rs. ").append(String.format("%.2f", totalAmount)).append("</strong></td></tr>")
                   .append("</tbody></table></div></div>");

        // Payment Information
        htmlBuilder.append("<div class=\"row mb-4\"><div class=\"col-md-12 text-center\">")
                   .append("<h5>Payment Information</h5>")
                   .append("<p>Payment Method: ").append(paymentMethod).append("<br>")
                   .append("Transaction ID: ").append(transactionId).append("<br>")
                   .append("Status: ").append(paymentStatus).append("</p>")
                   .append("</div></div>");

        // Footer Message
        htmlBuilder.append("<div class=\"footer\">")
                   .append("<p>Thank you for shopping with Furnii. We hope to see you again soon!</p>")
                   .append("</div></div>")
                   .append("</body></html>");

        return htmlBuilder.toString();
    }
}
