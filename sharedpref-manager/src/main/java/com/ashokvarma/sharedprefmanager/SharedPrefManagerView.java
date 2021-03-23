package com.ashokvarma.sharedprefmanager;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

/**
 * Class description
 *
 * @author ashokvarma
 * @version 1.0
 * @since 22 Jun 2017
 */
interface SharedPrefManagerView {
    @NonNull
    AppCompatActivity getViewContext();

    void setSubTitle(String subTitle);

    String getApplicationName();

    void setUpSpinner(Set<String> sharedPrefNames);

    void displaySharedPrefItems(ArrayList<SharedPrefItemModel> sharedPrefItemModelList);

    void showNoPrefItemsView();

    void changeSortIcon(@DrawableRes int drawableRes);

    void showAddEditPrefItemDialog(boolean isEdit, @Nullable SharedPrefItemModel sharedPrefItemModel, String selectedSharedPref, ArrayList<String> sharedPrefSupportedTypes, int preSelectTypePosition);

    void showDeleteConfirmationDialog(String sharedPrefName);
}
