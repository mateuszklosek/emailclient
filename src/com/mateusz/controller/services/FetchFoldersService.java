package com.mateusz.controller.services;

import com.mateusz.model.EmailTreeItem;
import com.mateusz.view.IconResolver;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import java.util.List;

public class FetchFoldersService extends Service {

    private Store store;
    private EmailTreeItem<String> folderRoot;
    private List<Folder> folderList;
    private IconResolver iconResolver = new IconResolver();

    public FetchFoldersService(Store store, EmailTreeItem<String> folderRoot, List<Folder> folderList) {
        this.store = store;
        this.folderRoot = folderRoot;
        this.folderList = folderList;
    }


    @Override
    protected Task createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                fetchFolders();
                return null;
            }
        };
    }

    private void fetchFolders() throws MessagingException {
        Folder[] folders = store.getDefaultFolder().list();
        handleFolders(folders, folderRoot);

    }

    private void handleFolders(Folder[] folders, EmailTreeItem<String> folderRoot) throws MessagingException {
        for(Folder folder: folders){
            folderList.add(folder);
            EmailTreeItem<String> emailTreeItem = new EmailTreeItem<String>(folder.getName());
            emailTreeItem.setGraphic(iconResolver.getIconForFolder(folder.getName()));
            folderRoot.getChildren().add(emailTreeItem);
            folderRoot.setExpanded(true);
            fetchMessagesOnFolder(folder, emailTreeItem);
            addMessageListenerToFolder(folder,emailTreeItem);
            if(folder.getType() == Folder.HOLDS_FOLDERS){
                Folder[] subFolders = folder.list();
                handleFolders(subFolders, emailTreeItem);
            }
        }
    }

    private void addMessageListenerToFolder(Folder folder, EmailTreeItem<String> emailTreeItem) {
        folder.addMessageCountListener(new MessageCountListener(){
            @Override
            public void messagesAdded(MessageCountEvent e) {
                for (int i = 0; i < e.getMessages().length; i++){
                    try {
                        Message message = folder.getMessage(folder.getMessageCount());
                        emailTreeItem.addEmailToTop(message);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void messagesRemoved(MessageCountEvent e) {
                System.out.println("message removed event" + e);
            }
        });
    }

    private void fetchMessagesOnFolder(Folder folder, EmailTreeItem<String> emailTreeItem) {
        Service fetchMessagesService = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        if(folder.getType() != folder.HOLDS_FOLDERS){
                            folder.open(Folder.READ_WRITE);
                            int folderSize = folder.getMessageCount();
                            for(int i = folderSize; i > 0; i--){
                                emailTreeItem.addEmail(folder.getMessage(i));
                            }
                        }
                        return null;
                    }
                };
            }
        };
        fetchMessagesService.start();
    }
}
