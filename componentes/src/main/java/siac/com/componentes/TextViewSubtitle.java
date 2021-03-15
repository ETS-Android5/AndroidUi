package siac.com.componentes;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class TextViewSubtitle extends FrameLayout {

    // COMPONENTES
    private TextView legendaTextView;
    private TextView descricaoTextView;

    // ATRIBUTOS
    private String legenda;
    private ColorStateList corLegenda;
    private float tamLegenda;

    private String descricao;
    private ColorStateList corDescricao;
    private float tamDescricao;
    private boolean singleLine;


    public TextViewSubtitle(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public TextViewSubtitle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public TextViewSubtitle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextViewLegenda, defStyleAttr, 0);
            legenda = typedArray.getString(R.styleable.TextViewLegenda_legenda);
            corLegenda = typedArray.getColorStateList(R.styleable.TextViewLegenda_corLegenda);
            tamLegenda = getSizeFontLegenda(typedArray.getString(R.styleable.TextViewLegenda_tamLegenda));

            descricao = typedArray.getString(R.styleable.TextViewLegenda_descricao);
            corDescricao = typedArray.getColorStateList(R.styleable.TextViewLegenda_corDescricao);
            tamDescricao = getSizeFontDescricao(typedArray.getString(R.styleable.TextViewLegenda_tamDescricao));
            singleLine = typedArray.getBoolean(R.styleable.TextViewLegenda_singleLine, false);
            return;
        }
        legenda = "legenda";
        tamLegenda = 11;
        descricao = "descricao";
        tamDescricao = 16;
    }

    private float getSizeFontLegenda(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.tamLegendaUi);
            return dimenPix / scaleRatio;
        }
    }

    private float getSizeFontDescricao(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.tamDescricaoUi);
            return dimenPix / scaleRatio;
        }
    }

    private void init() {
        inflate(getContext(), R.layout.view_textview_legenda_ui, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        descricaoTextView = findViewById(R.id.descricaoTextView);
        setup();
    }

    private void setup() {
        legendaTextView.setText(legenda);
        if(corLegenda != null) {
            legendaTextView.setTextColor(corLegenda);
        }
        legendaTextView.setTextSize(tamLegenda);

        descricaoTextView.setText(descricao);
        if(corDescricao != null) {
            descricaoTextView.setTextColor(corDescricao);
        }
        descricaoTextView.setTextSize(tamDescricao);

        descricaoTextView.setSingleLine(singleLine);

        //descricaoTextView.setSingleLine();
    }

    public void setDescricao(String descricao) {
        descricaoTextView.setText(descricao);
    }

    public void setCorDescricao(int color){
        descricaoTextView.setTextColor(ContextCompat.getColor(getContext(), color));
    }

}