package com.adapter.files;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.buscala.motorista.R;
import com.general.files.GeneralFunctions;
import com.view.MTextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * alterado em 24/03/2022.
 */
public class WalletHistoryRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    public GeneralFunctions generalFunc;
    ArrayList<HashMap<String, String>> list;
    Context mContext;
    boolean isFooterEnabled = false;
    View footerView;
    FooterViewHolder footerHolder;
    Locale locale = new Locale("pt","BR");//ALTERAÇÃO: NOVO LOCALE  INFORMADO

    public WalletHistoryRecycleAdapter(Context mContext, ArrayList<HashMap<String, String>> list,
                                       GeneralFunctions generalFunc, boolean isFooterEnabled) {
        this.mContext = mContext;
        this.list = list;
        this.generalFunc = generalFunc;
        this.isFooterEnabled = isFooterEnabled;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_list,
                    parent, false);
            this.footerView = v;
            return new FooterViewHolder(v);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallethistory_design,
                    parent, false);

            return new ViewHolder(view);
        }

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            final HashMap<String, String> item = list.get(position);
            final ViewHolder viewHolder = (ViewHolder) holder;

            viewHolder.transactiondateTxt.setText(item.get("listingFormattedDate"));//TODO: FORMATAR DA RECEBIDA, (ESTÁ DATA APARECE NO HISTPORICO DA CARTEIRA)
           // Log.i("TESTE DATA: ",item.get("listingFormattedDate"));

            viewHolder.transactionDesVTxt.setText(item.get("tDescriptionConverted"));


            NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String vls = item.get("FormattediBalance").replace("R$ ","");
            String valorRS = vls.replace("R$", "");
            double db = Double.parseDouble(valorRS);
            valorRS = currency.format(db);
            viewHolder.tranasctionBalVTxt.setText(valorRS);
            Log.i("TESTE  BalVTxt ",  valorRS);//TODO GEOVANE FORMATAR NUMEROS

           // viewHolder.tranasctionBalVTxt.setText(item.get("FormattediBalance"));



            if (item.get("eType").equalsIgnoreCase("Credit")) {
                viewHolder.arrowImg.setImageResource(R.drawable.ic_credit);
                viewHolder.colorview.setBackgroundColor(Color.parseColor("#2ea40f"));

            } else {
                viewHolder.arrowImg.setImageResource(R.drawable.ic_debit);
                viewHolder.colorview.setBackgroundColor(Color.parseColor("#c40001"));
            }

            // viewHolder.detailExpandArea.setId(position);
            viewHolder.contentArea.setId(position);
            viewHolder.transactionDetailArea.setId(position);


        } else {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            this.footerHolder = footerHolder;
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position) && isFooterEnabled == true) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionFooter(int position) {
        return position == list.size();
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (isFooterEnabled == true) {
            return list.size() + 1;
        } else {
            return list.size();
        }

    }

    public void addFooterView() {
        this.isFooterEnabled = true;
        notifyDataSetChanged();
        if (footerHolder != null)
            footerHolder.progressArea.setVisibility(View.VISIBLE);
    }

    public void removeFooterView() {
        if (footerHolder != null){
            isFooterEnabled=false;
            footerHolder.progressArea.setVisibility(View.GONE);
        }

    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView arrowImg;
        private MTextView tranasctionBalVTxt;
        private MTextView transactiondateTxt;
        private MTextView transactionDesVTxt;
        private LinearLayout transactionDetailArea;
        // private LinearLayout detailExpandArea;
        private LinearLayout contentArea;
        private View colorview;

        public ViewHolder(View view) {
            super(view);//alteracao realizada:

            arrowImg = (ImageView) view.findViewById(R.id.arrowImg);
            tranasctionBalVTxt = (MTextView) view.findViewById(R.id.tranasctionBalVTxt);
            transactiondateTxt = (MTextView) view.findViewById(R.id.transactiondateTxt);
            transactionDesVTxt = (MTextView) view.findViewById(R.id.transactionDesVTxt);
            transactionDetailArea = (LinearLayout) view.findViewById(R.id.transactionDetailArea);
            //   detailExpandArea = (LinearLayout) view.findViewById(R.id.detailExpandArea);
            contentArea = (LinearLayout) view.findViewById(R.id.contentArea);
            colorview = view.findViewById(R.id.view);

        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout progressArea;

        public FooterViewHolder(View itemView) {
            super(itemView);

            progressArea = (LinearLayout) itemView;

        }
    }
}
