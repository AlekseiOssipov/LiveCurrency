package com.example.livecurrency;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.util.ArrayList;
import java.io.StringReader;

public class UserXmlParser {

    private ArrayList<Currency> currencies;

    public UserXmlParser(){
        currencies = new ArrayList<>();
    }

    public ArrayList<Currency> getCurrencies(){
        return  currencies;
    }

    public boolean parse(String xmlData){
        boolean status = true;
        Currency currentCurrency = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("Cube".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentCurrency = new Currency();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("Cube".equalsIgnoreCase(tagName)){
                                currencies.add(currentCurrency);
                                inEntry = false;
                            } else if("currency".equalsIgnoreCase(tagName)){
                                currentCurrency.setCurrency(textValue);
                            } else if("rate".equalsIgnoreCase(tagName)){
                                currentCurrency.setRate(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return  status;
    }
}