package com.papanews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class Adapter extends FragmentPagerAdapter {


    private String isnoksip[] = new String[]{"Profile", "Recommended", "Technology", "Politics",
            "Business", "Startup", "Entertainment", "Sports", "International", "Influencer", "Miscellaneous"};

    private String isskip[] = new String[]{"Profile", "Technology", "Politics",
            "Business", "Startup", "Entertainment", "Sports", "International", "Influencer", "Miscellaneous"};

//    private String views_link[] = new String[]{"", "Tech", "Politics",
//            "Business", "Startup", "Entertain", "Sports", "International", "Influence", "Miscel"};

    private String tabTitles[] = new String[]{};

    Context context;
    FragmentManager fragmentManager;

    public Adapter(@NonNull final FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentManager =fm;
        Log.e("fmprint ", String.valueOf(fm));
//        fm.beginTransaction().replace(R.id.viewPager,NewsFragment.class,null,"Politics").setReorderingAllowed(true).addToBackStack(null).commit();
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public Fragment getItem(int position) {

        Log.e("globalskip ", global.skipskip);

        if (!global.skipskip.equals("skip")) {
            Log.e("hbhbbhbh ", "yessss");

            switch (position) {
                case 0:
                    return new Profile();
                default:
                    Fragment f1 = new NewsFragment2();
                    Bundle args1 = new Bundle();
                    args1.putString("frag_shared", isnoksip[position]);
                    global.frag_shared = isnoksip[position];
//                    args1.putString("views_link", views_link[position]);
                    f1.setArguments(args1);

//                    Technology.newInstance(isnoksip[position], views_link[position]);
//                    Fragment f1 = new NewsFragment();
//                    Bundle args1 = new Bundle();
//                    args1.putString("frag_shared", isnoksip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f1.setArguments(args1);

//                    FragmentManager fragmentManager = fm;
//                    fragmentManager.beginTransaction()
//                            .replace(R.layout.item_card, f1, isskip[position])
//                            .commit();
                    return f1;

//                case 1:
//                    Fragment f2 = new NewsFragment2();
//                    Bundle args2 = new Bundle();
//                    args2.putString("frag_shared", isnoksip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f2.setArguments(args2);
//                    return f2;
//                case 2:
//                    Fragment f3 = new NewsFragment3();
//                    Bundle args3 = new Bundle();
//                    args3.putString("frag_shared", isnoksip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f3.setArguments(args3);
//                    return f3;
//                default:
//                    return null;
//                case 3:
//                    return new Politics();
//                case 4:
//                    return new Business();
//                case 5:
//                    return new Startup();
//                case 6:
//                    return new entertaintment();
//                case 7:
//                    return new sports();
//                case 8:
//                    return new International();
//                case 9:
//                    return new Influencer();
//                case 10:
//                    return new miscellaneous();
            }
        } else {
            Log.e("hbhbbhbh ", position+" -  2");
            switch (position) {
                case 0:
                    return new Profile();
//                case 1:
//                    Fragment f2 = new NewsFragment2();
//                    Bundle args2 = new Bundle();
//                    args2.putString("frag_shared", isskip[position]);
//                    global.frag_shared = isskip[position];
////                    args1.putString("views_link", views_link[position]);
//                    f2.setArguments(args2);
//                    return f2;
//                case 2:
//                    Fragment f3 = new NewsFragment3();
//                    Bundle args3 = new Bundle();
//                    args3.putString("frag_shared", isskip[position]);
//                    global.frag_shared = isskip[position];
//
////                    args1.putString("views_link", views_link[position]);
//                    f3.setArguments(args3);
//                    return f3;
//                case 3:
//                    Fragment f4 = new NewsFragment4();
//                    Bundle args4 = new Bundle();
//                    args4.putString("frag_shared", isskip[position]);
//                    global.frag_shared = isskip[position];
//
////                    args1.putString("views_link", views_link[position]);
//                    f4.setArguments(args4);
//                    return f4;
//                case 4:
//                    Fragment f5 = new NewsFragment5();
//                    Bundle args5 = new Bundle();
//                    args5.putString("frag_shared", isskip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f5.setArguments(args5);
//                    return f5;
//                case 5:
//                    Fragment f6 = new NewsFragment6();
//                    Bundle args6 = new Bundle();
//                    args6.putString("frag_shared", isskip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f6.setArguments(args6);
//                    return f6;
//                case 6:
//                    Fragment f7 = new NewsFragment7();
//                    Bundle args7 = new Bundle();
//                    args7.putString("frag_shared", isskip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f7.setArguments(args7);
//                    return f7;
//                case 7:
//                    Fragment f8 = new NewsFragment8();
//                    Bundle args8 = new Bundle();
//                    args8.putString("frag_shared", isskip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f8.setArguments(args8);
//                    return f8;
//                case 8:
//                    Fragment f9 = new NewsFragment9();
//                    Bundle args9 = new Bundle();
//                    args9.putString("frag_shared", isskip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f9.setArguments(args9);
//                    return f9;
//                case 9:
//                    Fragment f11 = new NewsFragment11();
//                    Bundle args11 = new Bundle();
//                    args11.putString("frag_shared", isskip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f11.setArguments(args11);
//                    return f11;
//                case 10:
//                    Fragment f12 = new NewsFragment();
//                    Bundle args12 = new Bundle();
//                    args12.putString("frag_shared", isskip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f12.setArguments(args12);
//                    return f12;
//                case 11:
//                    Fragment f = new NewsFragment();
//                    Bundle args = new Bundle();
//                    args.putString("frag_shared", isskip[position]);
////                    args1.putString("views_link", views_link[position]);
//                    f.setArguments(args);
//                    return f;
                default:
//                    Technology.newInstance(isskip[position], views_link[position]);
//                    Technology.frag_shared = isskip[position];
                    Fragment f1 = new NewsFragment2();
                    Bundle args1 = new Bundle();
                    args1.putString("frag_shared", isskip[position]);
                    global.frag_shared = isskip[position];
//                    args1.putString("views_link", views_link[position]);
                    f1.setArguments(args1);
//                    fragmentManager.beginTransaction().add(R.id.view_pager,f1,isskip[position]).commit();

//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    fragmentManager.beginTransaction()
//                            .replace(R.layout.item_card, f1,isskip[position] )
//                            .commit();
                    Log.e("fragtag - ", f1.getTag()+" - "+isskip[position] );

                    return f1;

//                default:
//                    return null;
            }
        }
//                case 2:
//                    return new Politics();
//                case 3:
//                    return new Business();
//                case 4:
//                    return new Startup();
//                case 5:
//                    return new entertaintment();
//                case 6:
//                    return new sports();
//                case 7:
//                    return new International();
//                case 8:
//                    return new Influencer();
//                case 9:
//                    return new miscellaneous();
//            }
//        }

        //return null;
    }

    @Override
    public int getCount() {
        return global.returnahowmuch;
    }

    public CharSequence getPageTitle(int position) {

        if (global.skipskip.equals("skip")) {
            tabTitles = isskip;
        } else {
            tabTitles = isnoksip;
        }

        return tabTitles[position];
    }


}
