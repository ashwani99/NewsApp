package com.newsful5.android.list;

public class LinkAddress {
    public static int listno;
    private static String[][] sites = {
            {
                    "Geek.com",
                    "wired.com",
                    "PcWorld",
                    "TechCrunch",
                    "EnGadget",
                    "ThinkGeek",
                    "MakeUseOf",
                    "lifehacker",
                    "linuxjournalcom",
                    "Gigaom",
                    "CNN-Tech News",
                    "Time-Tech News",
                    "CBC-Tech News"
            },
            //News list
            {
                    "CNN-Top Stories",
                    "Time",
                    "CBC News",
                    "BBC",
                    "CBC Politics",
                    "CBC Business",
                    "Time Politics",
                    "Time Business",
                    "RCP",
                    "CBC World",
                    "Global Spin-Time",
                    "Asia",
                    "America",
                    "Africa",
                    "Europe",
                    "Middle East",
                    "CNN-US"},
            //Gaming Sites
            {
                    "JoyStiq",
                    "Kotaku",
                    "GameSpot-Reviews",
                    "TechRaptor",
                    "GameSpot-MashUp",
                    "GameSpot-News",
                    "Gizmodo",
                    "jayisgames"
            },
            //PhotoGraphy start
            {
                    "Time-LightBox",
                    "But Does it float",
                    "The impossible cool",
                    "PhotoFocus",
                    "Dear Photograph",
                    "Photo focus",
                    "The luminous landscape",
                    "Prolost",
                    "Photography Served",
                    "PhobloGrapher"
            },
            //lifestyle starts
            {
                    "PSFK",
                    "Peanut Butter Fingers",
                    "Life Listed",
                    "KMP Blog",
                    "GBlog",
                    "KindNess Blog",
                    "The real deal by retailMeNot",
                    "ashy to classy",
                    "glassoscotch"
            },
            //sports starts
            {
                    "Sports",
                    "MLB",
                    "NBA",
                    "curling",
                    "CFL",
                    "NFL",
                    "NHL",
                    "Soccer",
                    "Figure Skating"
            },
            //hollywood
            {
                    "Popsugar",
                    "CBC-Entertainment",
                    "Empire News",
                    "Film School Rejects",
                    "Hollywood Life",
                    "The Daily swarm"
                    , "All HipHop",
                    "Your Music Today",

            },
            //Books
            {
                    "The New York Review of Books",
                    "Pixel of ink",
                    "Powell's Books",
                    "writer beware",
                    "IndieReader",
                    "RTBookReviews",
            },
            //Health
            {
                    "Time Health",
                    "MedLinePlus",
                    "Holy Kaw!!",
                    "CBC-Health",

                    "Health.com-Diet and Fitness",
                    "Health.com-Happy and Healthy",
                    "Eating well-MakeItTonight",
                    "Eating Well-Menus",
                    "Eating Well-Blog"

            }
    };
    private static String[][] site_des = {
            {
                    "Geeking out since 1995. Providing readers with tech news, reviews, and tips.",
                    "Get in-depth coverage of current and future trends in technology, and how they are shaping business, entertainment, communications, science, politics, and ...",
                    "Covering everything from ultrabooks to servers, from Windows 8 to virtualization, PCWorld delivers the information and expert advice you need to get the job ..",
                    "TechCrunch Events. Disrupt · Crunchies · Meetups · International City Events · Hackathon · Hardware Battlefield · Include. News About. Google I/O 2014 · CES ...",
                    "Engadget is a web magazine with obsessive daily coverage of everything new in gadgets and consumer electronics",
                    "ThinkGeek creates unique products that stimulate the imagination. Shop for apparel, home and office, gadgets, collectibles, and more.",
                    "MakeUseOf is your guide in modern tech. Learn how to make use of tech and gadgets around you and discover cool stuff on the Internet.",
                    "Learn tips and tricks for everything from computers to brewing coffee from the team at Lifehacker. ",
                    "The monthly magazine of the Linux community, promoting the use of Linux worldwide.",
                    "Technology news, trends and analysis covering mobile, big data, cloud, science, energy and media."
            },

            {
                    "Geeking out since 1995. Providing readers with tech news, reviews, and tips.",
                    "Get in-depth coverage of current and future trends in technology, and how they are shaping business, entertainment, communications, science, politics, and ...",
                    "Covering everything from ultrabooks to servers, from Windows 8 to virtualization, PCWorld delivers the information and expert advice you need to get the job ..",
                    "TechCrunch Events. Disrupt · Crunchies · Meetups · International City Events · Hackathon · Hardware Battlefield · Include. News About. Google I/O 2014 · CES ...",
                    "Engadget is a web magazine with obsessive daily coverage of everything new in gadgets and consumer electronics",
                    "ThinkGeek creates unique products that stimulate the imagination. Shop for apparel, home and office, gadgets, collectibles, and more.",
                    "MakeUseOf is your guide in modern tech. Learn how to make use of tech and gadgets around you and discover cool stuff on the Internet.",
                    "Learn tips and tricks for everything from computers to brewing coffee from the team at Lifehacker. ",
                    "The monthly magazine of the Linux community, promoting the use of Linux worldwide.",
                    "Technology news, trends and analysis covering mobile, big data, cloud, science, energy and media."
            }
    };

    public static String[] getLink(int no) {
        return sites[no];
    }

    public static String[] getDescription(int no) {
        return site_des[no];
    }
}
