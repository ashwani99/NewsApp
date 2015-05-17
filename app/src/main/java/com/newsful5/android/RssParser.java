package com.newsful5.android;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class RssParser {

    private final String ns = null;

    public List<RssItem> parse(InputStream inputStream) throws XmlPullParserException, IOException {

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
         try {
             inputStream.close();
         }catch (NullPointerException e)
         {
             e.printStackTrace();
         }
         }
    }

    private List<RssItem> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "rss");
        String title = null;
        int flag = 0;
        int count = 0;
        String link = null;
        String description = null;
        String thumbnail = null;
        List<RssItem> items = new ArrayList<RssItem>();
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            System.out.println("namd" + name);

            if (name.equals("item")) {
                flag = 1;
                count = 0;
            }
            if (name.equals("title") && flag == 1 && count < 4) {
                title = readTitle(parser);
                count++;

            } else if (name.equals("link") && flag == 1 && count < 4) {
                link = readLink(parser);
                count++;
            } else if (name.equals("description") && flag == 1 && count < 4) {
                description = readDescription(parser);

                count++;

                thumbnail = pullImageLink(description);

                if (thumbnail != null)
                    count++;

            }
            else if (name.equals("media:thumbnail") && flag == 1 && count < 4) {
                thumbnail = readThumbnail(parser);
                count++;
                // flag=0;
            }
            if (title != null && link != null && description != null && thumbnail != null) {

                RssItem item = new RssItem(title, link, description, thumbnail);
                items.add(item);
                title = null;
                link = null;
                description = null;
                thumbnail = null;
            }
        }
        return items;
    }

    private String readLink(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String link = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return link;
    }

    private String readThumbnail(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "media:thumbnail");
        String thumbnailUrl = parser.getAttributeValue(null, "url");
        parser.nextTag();
        return thumbnailUrl;
    }

    private String readTitle(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }

    private String readDescription(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "description");
        String description = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "description");
        return description;
    }

    private String extractCData(String data) {

        data = data.replaceAll("\\<.*?>", "");
        data = data.replaceAll("\n", "");
        return data;
    }

    private String pullImageLink(String encoded) {

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser xpp = factory.newPullParser();


            xpp.setInput(new StringReader(encoded));

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG && "img".equals(xpp.getName())) {

                    int count = xpp.getAttributeCount();

                    for (int x = 0; x < count; x++) {

                        if (xpp.getAttributeName(x).equalsIgnoreCase("src"))

                            return xpp.getAttributeValue(x).replaceAll("-110x52", "");

                    }

                }

                eventType = xpp.next();

            }

        } catch (Exception e) {


        }


        return null;

    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

}