package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.GeneralPlaylistViewController;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;

public class GeneralPlaylistView extends JPanel {

    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;

    public GeneralPlaylistView(HeaderView headerView,FooterView footerView, Utils utils){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
    }

    public void addGeneralPlaylistController(GeneralPlaylistViewController generalPlaylistViewController){
        //set action command
    }


    public void configureGeneralPlaylistView() {
        Icon statisticsBtn = new ImageIcon(String.valueOf(AssetsFiles.LISTMANAGING_LABEL));
        add(headerView.configureHeader(statisticsBtn));
        add(footerView.configureFooter());

    }
}
