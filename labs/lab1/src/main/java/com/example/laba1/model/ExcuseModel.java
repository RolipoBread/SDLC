package com.example.laba1.model;

import java.util.ArrayList;
import java.util.List;

public class ExcuseModel {

    public interface ModelListener {
        void onModelChanged();
    }

    private final List<ModelListener> listeners = new ArrayList<>();

    private Situation currentSituation = Situation.УЧЕБА;
    private String lastExcuse = "";
    private String savedName = "";
    private String savedDetails = "";

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ModelListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.onModelChanged();
        }
    }

    public void setCurrentSituation(Situation situation) {
        this.currentSituation = situation;
        notifyListeners();
    }

    public void setLastExcuse(String excuse) {
        this.lastExcuse = excuse;
        notifyListeners();
    }

    public void setUserData(String name, String details) {
        this.savedName = name;
        this.savedDetails = details;
        notifyListeners();
    }

    public Situation getCurrentSituation() { return currentSituation; }
    public String getLastExcuse() { return lastExcuse; }
    public String getSavedName() { return savedName; }
    public String getSavedDetails() { return savedDetails; }

    public boolean hasUserData() {
        return savedName != null && !savedName.isBlank();
    }
}