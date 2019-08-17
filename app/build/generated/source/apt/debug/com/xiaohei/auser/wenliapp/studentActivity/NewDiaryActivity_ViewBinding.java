// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.studentActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewDiaryActivity_ViewBinding implements Unbinder {
  private NewDiaryActivity target;

  @UiThread
  public NewDiaryActivity_ViewBinding(NewDiaryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewDiaryActivity_ViewBinding(NewDiaryActivity target, View source) {
    this.target = target;

    target.ly_return = Utils.findRequiredViewAsType(source, R.id.ly_return, "field 'ly_return'", RelativeLayout.class);
    target.img_send = Utils.findRequiredViewAsType(source, R.id.img_send, "field 'img_send'", ImageView.class);
    target.diary_study = Utils.findRequiredViewAsType(source, R.id.diary_study, "field 'diary_study'", RadioGroup.class);
    target.diary_study_1 = Utils.findRequiredViewAsType(source, R.id.diary_study_1, "field 'diary_study_1'", RadioButton.class);
    target.diary_study_2 = Utils.findRequiredViewAsType(source, R.id.diary_study_2, "field 'diary_study_2'", RadioButton.class);
    target.diary_study_3 = Utils.findRequiredViewAsType(source, R.id.diary_study_3, "field 'diary_study_3'", RadioButton.class);
    target.diary_heath = Utils.findRequiredViewAsType(source, R.id.diary_heath, "field 'diary_heath'", RadioGroup.class);
    target.diary_heath_1 = Utils.findRequiredViewAsType(source, R.id.diary_heath_1, "field 'diary_heath_1'", RadioButton.class);
    target.diary_heath_2 = Utils.findRequiredViewAsType(source, R.id.diary_heath_2, "field 'diary_heath_2'", RadioButton.class);
    target.diary_heath_3 = Utils.findRequiredViewAsType(source, R.id.diary_heath_3, "field 'diary_heath_3'", RadioButton.class);
    target.diary_mood = Utils.findRequiredViewAsType(source, R.id.diary_mood, "field 'diary_mood'", RadioGroup.class);
    target.diary_mood_1 = Utils.findRequiredViewAsType(source, R.id.diary_mood_1, "field 'diary_mood_1'", RadioButton.class);
    target.diary_mood_2 = Utils.findRequiredViewAsType(source, R.id.diary_mood_2, "field 'diary_mood_2'", RadioButton.class);
    target.diary_mood_3 = Utils.findRequiredViewAsType(source, R.id.diary_mood_3, "field 'diary_mood_3'", RadioButton.class);
    target.diary_consume = Utils.findRequiredViewAsType(source, R.id.diary_consume, "field 'diary_consume'", RadioGroup.class);
    target.diary_consume_1 = Utils.findRequiredViewAsType(source, R.id.diary_consume_1, "field 'diary_consume_1'", RadioButton.class);
    target.diary_consume_2 = Utils.findRequiredViewAsType(source, R.id.diary_consume_2, "field 'diary_consume_2'", RadioButton.class);
    target.diary_consume_3 = Utils.findRequiredViewAsType(source, R.id.diary_consume_3, "field 'diary_consume_3'", RadioButton.class);
    target.diary_return = Utils.findRequiredViewAsType(source, R.id.diary_return, "field 'diary_return'", RadioGroup.class);
    target.diary_return_1 = Utils.findRequiredViewAsType(source, R.id.diary_return_1, "field 'diary_return_1'", RadioButton.class);
    target.diary_return_2 = Utils.findRequiredViewAsType(source, R.id.diary_return_2, "field 'diary_return_2'", RadioButton.class);
    target.diary_return_3 = Utils.findRequiredViewAsType(source, R.id.diary_return_3, "field 'diary_return_3'", RadioButton.class);
    target.diary_sleep = Utils.findRequiredViewAsType(source, R.id.diary_sleep, "field 'diary_sleep'", RadioGroup.class);
    target.diary_sleep_1 = Utils.findRequiredViewAsType(source, R.id.diary_sleep_1, "field 'diary_sleep_1'", RadioButton.class);
    target.diary_sleep_2 = Utils.findRequiredViewAsType(source, R.id.diary_sleep_2, "field 'diary_sleep_2'", RadioButton.class);
    target.diary_sleep_3 = Utils.findRequiredViewAsType(source, R.id.diary_sleep_3, "field 'diary_sleep_3'", RadioButton.class);
    target.diary_lovelose = Utils.findRequiredViewAsType(source, R.id.diary_lovelose, "field 'diary_lovelose'", RadioGroup.class);
    target.diary_lovelose_1 = Utils.findRequiredViewAsType(source, R.id.diary_lovelose_1, "field 'diary_lovelose_1'", RadioButton.class);
    target.diary_lovelose_2 = Utils.findRequiredViewAsType(source, R.id.diary_lovelose_2, "field 'diary_lovelose_2'", RadioButton.class);
    target.diary_lovelose_3 = Utils.findRequiredViewAsType(source, R.id.diary_lovelose_3, "field 'diary_lovelose_3'", RadioButton.class);
    target.diary_text = Utils.findRequiredViewAsType(source, R.id.diary_text, "field 'diary_text'", EditText.class);
    target.textView_maxnumber = Utils.findRequiredViewAsType(source, R.id.maxnumber, "field 'textView_maxnumber'", TextView.class);
    target.layout = Utils.findRequiredViewAsType(source, R.id.ll_layout, "field 'layout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewDiaryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ly_return = null;
    target.img_send = null;
    target.diary_study = null;
    target.diary_study_1 = null;
    target.diary_study_2 = null;
    target.diary_study_3 = null;
    target.diary_heath = null;
    target.diary_heath_1 = null;
    target.diary_heath_2 = null;
    target.diary_heath_3 = null;
    target.diary_mood = null;
    target.diary_mood_1 = null;
    target.diary_mood_2 = null;
    target.diary_mood_3 = null;
    target.diary_consume = null;
    target.diary_consume_1 = null;
    target.diary_consume_2 = null;
    target.diary_consume_3 = null;
    target.diary_return = null;
    target.diary_return_1 = null;
    target.diary_return_2 = null;
    target.diary_return_3 = null;
    target.diary_sleep = null;
    target.diary_sleep_1 = null;
    target.diary_sleep_2 = null;
    target.diary_sleep_3 = null;
    target.diary_lovelose = null;
    target.diary_lovelose_1 = null;
    target.diary_lovelose_2 = null;
    target.diary_lovelose_3 = null;
    target.diary_text = null;
    target.textView_maxnumber = null;
    target.layout = null;
  }
}
