package com.mateusz;

import com.mateusz.controller.services.FetchFoldersService;
import com.mateusz.model.EmailAccount;
import com.mateusz.model.EmailTreeItem;
import javafx.scene.control.TreeItem;

public class EmailManger {
    //Folder handling:
    private EmailTreeItem<String> folderRoot = new EmailTreeItem<String>("");

    public EmailTreeItem<String> getFolderRoot(){
        return folderRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount){
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem);
        fetchFoldersService.start();
        folderRoot.getChildren().add(treeItem);
    }
}
