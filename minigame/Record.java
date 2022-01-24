package com.example.minigame;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

@SuppressWarnings("deprecation")

public class Record extends TabActivity {

    TextView myNana, myDdubi, myBoradori, myBbo;
    TextView nanaRank, ddubiRank, boradoriRank, bboRank;
    myDBHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        myNana = (TextView) findViewById(R.id.myNana);
        myDdubi = (TextView) findViewById(R.id.myDdubi);
        myBoradori = (TextView) findViewById(R.id.myBoradori);
        myBbo = (TextView) findViewById(R.id.myBbo);

        nanaRank = (TextView) findViewById(R.id.nanaRank);
        ddubiRank = (TextView) findViewById(R.id.ddubiRank);
        boradoriRank = (TextView) findViewById(R.id.boradoriRank);
        bboRank = (TextView) findViewById(R.id.bboRank);

        myHelper = new myDBHelper(this);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecNana = tabHost.newTabSpec("Nana").setIndicator("나나");
        tabSpecNana.setContent(R.id.tabNana);
        tabHost.addTab(tabSpecNana);

        TabHost.TabSpec tabSpecDdubi = tabHost.newTabSpec("Ddubi").setIndicator("뚜비");
        tabSpecDdubi.setContent(R.id.tabDdubi);
        tabHost.addTab(tabSpecDdubi);

        TabHost.TabSpec tabSpecBoradori = tabHost.newTabSpec("Boradori").setIndicator("보라돌이");
        tabSpecBoradori.setContent(R.id.tabBoradori);
        tabHost.addTab(tabSpecBoradori);

        TabHost.TabSpec tabSpecBbo = tabHost.newTabSpec("Bbo").setIndicator("뽀");
        tabSpecBbo.setContent(R.id.tabBbo);
        tabHost.addTab(tabSpecBbo);

        tabHost.setCurrentTab(0);

        nanaRank.setText(myHelper.nanaGetResult());
        ddubiRank.setText(myHelper.ddubiGetResult());
        boradoriRank.setText(myHelper.boradoriGetResult());
        bboRank.setText(myHelper.bboGetResult());




    }


}
