package com.newsful5.android;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Toast;

import com.newsful5.android.list.LinkAddress;
import com.newsful5.android.list.MyDBHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RssService extends IntentService {

    public static final String ITEMS = "items";
    public static final String RECEIVER = "receiver";
    private int position;
    private String tableName;
    private MyDBHandler mHandler;

    public RssService() {
        super("RssService");
    }

    private String[] table_name = {"tech_table", "news_table", "game_table", "photo_table", "lifestyle_table",
            "sports_table", "movie_table", "books_table", "food_table", "music_table"};

    private String linkArr[][] = {

            //Technology feeds
            {
            "http://www.geek.com/feed/",
            "http://www.wired.com/feed/",
            "http://www.pcworld.com/index.rss",
            "http://techcrunch.com/feed/",
            "http://www.engadget.com/rss.xml",
            "http://feeds.feedburner.com/thinkgeek/whatsnew",
            "http://feeds.feedburner.com/makeuseof",
            "http://feeds.gawker.com/lifehacker/full",
            "http://feeds.feedburner.com/linuxjournalcom",
            "https://gigaom.com/feed/",
            "http://rss.cnn.com/rss/edition_technology.rss",
            "http://time.com/tech/feed/",
            "http://rss.cbc.ca/lineup/technology.xml",

    },
            //news feeds
            {
             "http://rss.cnn.com/rss/edition.rss",
             "http://feeds2.feedburner.com/time/topstories",
             "http://rss.cbc.ca/lineup/topstories.xml",
             "http://feeds.bbci.co.uk/news/rss.xml?",
             "http://rss.cbc.ca/lineup/politics.xml",
             "http://rss.cbc.ca/lineup/business.xml",
             "http://time.com/politics/feed/",
             "http://feeds2.feedburner.com/time/business",
             "http://www.realclearpolitics.com/index.xml",
             "http://rss.cbc.ca/lineup/world.xml",
             "http://feeds2.feedburner.com/timeblogs/globalspin",
             "http://rss.cnn.com/rss/edition_asia.rss",
             "http://rss.cnn.com/rss/edition_americas.rss",
             "http://rss.cnn.com/rss/edition_africa.rss",
             "http://rss.cnn.com/rss/edition_europe.rss",

            },
            //Gaming feeds
            {
              "http://www.joystiq.com//rss.xml",
              "http://feeds.gawker.com/kotaku/full",
              "http://www.gamespot.com/feeds/reviews/",
              "http://techraptor.net/feed",
              "http://www.gamespot.com/feeds/mashup/",
              "http://www.gamespot.com/feeds/news/",
              "http://feeds.gawker.com/gizmodo/full",
              "http://jayisgames.com/index.xml"

            },
            {
              "http://lightbox.time.com/feed/",
               "http://feeds2.feedburner.com/ButDoesItFloat?format=xml",
               "http://theimpossiblecool.tumblr.com/rss",
               "http://photofocus.com/feed/",
               "http://dearphotograph.com/rss",
               "http://photofocus.com/feed/",
               "http://feeds2.feedburner.com/luminous-landscape-whatsnew",
               "http://prolost.com/blog?format=RSS",
               "http://www.photographyserved.com/feed/projects",
               "http://thephoblographer.com.feedsportal.com/c/35278/f/657470/index.rss"

            },
            //Lifestyle
            {

                    "http://www.psfk.com/feeds/psfk",
                    "http://www.pbfingers.com/feed/",
                    "http://feedpress.me/lifelisted",
                    "http://kmpblog.com/feed/",
                    "http://blog.gessato.com/feed/",
                    "http://kindnessblog.com/feed/",
                    "http://www.retailmenot.com/blog/feed",
                    "http://ashy2classy.net/feed/",
                    "https://glassoscotch.wordpress.com/feed/"

            },
            //sports
            {
                    "http://www.cbc.ca/cmlink/rss-sports",
                    "http://rss.cbc.ca/lineup/sports-mlb.xml",
                    "http://rss.cbc.ca/lineup/sports-nba.xml",
                    "http://rss.cbc.ca/lineup/sports-curling.xml",
                    "http://rss.cbc.ca/lineup/sports-cfl.xml",
                    "http://rss.cbc.ca/lineup/sports-nfl.xml",
                    "http://rss.cbc.ca/lineup/sports-nhl.xml",
                    "http://rss.cbc.ca/lineup/sports-soccer.xml",
                    "http://rss.cbc.ca/lineup/sports-figureskating.xml",
            },

            //movie
            {
                    "http://www.popsugar.com/celebrity/feed",
                    "http://www.cbc.ca/cmlink/rss-arts",
                    "http://www.empireonline.co.uk/rss/rss.asp",
                    "http://filmschoolrejects.com/tag/creature-feature/feed",

                    "http://hollywoodlife.com/feed/",
                    "http://dailyswarm.com.feedsportal.com/c/35235/f/655017/index.rss",
                    "http://allhiphop.com/feed/",
                    "http://www.yourmusictoday.com/feed/",



            },
            //Books and novels starts
            {
                "http://feeds.feedburner.com/nybooks",
                "https://ilmk.wordpress.com/feed/",

                    "http://www.powells.com/rss/powells_content.xml",
                    "http://feeds.feedburner.com/AtLastWriterBewareBlogsAcCrispinAndVictoriaStraussRevealAll?format=xml",
                    "http://indiereader.com/feed/",
                    "http://www.rtbookreviews.com/taxonomy/term/381/all/feed"

            },
            {
                    "http://time.com/health/feed/",
                    "http://www.nlm.nih.gov/medlineplus/feeds/whatsnew_en.xml",
                    "http://holykaw.alltop.com/feed",
                    "http://www.cbc.ca/cmlink/1.306",

                    "http://www.health.com/health/diet-fitness/feed",
                    "http://www.health.com/health/healthy-happy/feed",
                    "http://feeds.feedburner.com/eatingwell_make_it_tonight?format=xml",
                    "http://feeds.feedburner.com/eatingwell_menus?format=xml",
                    "http://feeds.feedburner.com/EatingwellBlogs-AllBlogPosts?format=xml"


            }



    };

    @Override
    protected void onHandleIntent(Intent intent) {

        List<RssItem> rssItems = null;
        List<RssItem> rssItems1 = null;
        int count = 0;

        Bundle positionbundle = new Bundle();
        positionbundle = intent.getBundleExtra("position");
        position = positionbundle.getInt("position");

        setUpDatabase(position);
        createOrCheckDatabase(table_name[position], position);
        for (int i = 0; i < linkArr[position].length; i++) {
            if (isChecked(i)) {
                count = 1;
                break;
            }
        }
        if (count != 1) {

        } else {

            int i;
            for (i = 0; i < linkArr[position].length; i++) {

                if (isChecked(i)) {

                    try {
                        RssParser parser = new RssParser();
                        InputStream instrm=getInputStream(linkArr[position][i]);
                        if(instrm!=null){
                        rssItems = parser.parse(instrm);
                        break;}else{
                            Toast.makeText(getApplicationContext(),"Please check your Internet Connection",Toast.LENGTH_SHORT).show();
                        }

                    } catch (XmlPullParserException e) {
                        Log.w(e.getMessage(), e);
                    } catch (IOException e) {
                        Log.w(e.getMessage(), e);
                    }
                }
            }

            for (int j = i+1; j < linkArr[position].length; j++) {
                if (isChecked(j)) {
                    try {
                        RssParser parser = new RssParser();
                        InputStream is=getInputStream(linkArr[position][j]);
                        if(is!=null){
                        rssItems1 = parser.parse(is);
                        rssItems.addAll(rssItems1);}else{
                            Toast.makeText(getApplicationContext(),"Please check your Internet Connection",Toast.LENGTH_SHORT).show();
                        }
                    } catch (XmlPullParserException e) {
                        Log.w(e.getMessage(), e);
                    } catch (IOException e) {
                        Log.w(e.getMessage(), e);
                    }

                }

            }
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEMS, (Serializable) rssItems);
        ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
        receiver.send(0, bundle);
    }

    private void setUpDatabase(int position) {
        tableName = table_name[position];
        mHandler = new MyDBHandler(this);
        mHandler.TABLE_NAME = tableName;

    }



    public InputStream getInputStream(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream stream = conn.getInputStream();
            return stream;
        } catch (IOException e) {

            return null;
        }
    }

    Boolean isChecked(int id) {

        if (mHandler.getLink(id + 1) == 1)
            return true;
        return false;
    }

    private void createOrCheckDatabase(String name, int pos) {
        mHandler = new MyDBHandler(this);
        mHandler.TABLE_NAME = name;
        String[] list = LinkAddress.getLink(pos);
        mHandler.createTable(list);

    }


}