// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.studentActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShowWeekActivity_ViewBinding implements Unbinder {
  private ShowWeekActivity target;

  @UiThread
  public ShowWeekActivity_ViewBinding(ShowWeekActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShowWeekActivity_ViewBinding(ShowWeekActivity target, View source) {
    this.target = target;

    target.ly_return = Utils.findRequiredViewAsType(source, R.id.ly_return, "field 'ly_return'", RelativeLayout.class);
    target.week_time = Utils.findRequiredViewAsType(source, R.id.week_time, "field 'week_time'", TextView.class);
    target.week_studycondition = Utils.findRequiredViewAsType(source, R.id.week_studycondition, "field 'week_studycondition'", TextView.class);
    target.week_heathcondition = Utils.findRequiredViewAsType(source, R.id.week_heathcondition, "field 'week_heathcondition'", TextView.class);
    target.week_returncondition = Utils.findRequiredViewAsType(source, R.id.week_returncondition, "field 'week_returncondition'", TextView.class);
    target.week_sleepcondition = Utils.findRequiredViewAsType(source, R.id.week_sleepcondition, "field 'week_sleepcondition'", TextView.class);
    target.week_moodcondition = Utils.findRequiredViewAsType(source, R.id.week_moodcondition, "field 'week_moodcondition'", TextView.class);
    target.week_consumecondition = Utils.findRequiredViewAsType(source, R.id.week_consumecondition, "field 'week_consumecondition'", TextView.class);
    target.week_lovelosecondition = Utils.findRequiredViewAsType(source, R.id.week_lovelosecondition, "field 'week_lovelosecondition'", TextView.class);
    target.week_conditiontext = Utils.findRequiredViewAsType(source, R.id.week_conditiontext, "field 'week_conditiontext'", TextView.class);
    target.week_text = Utils.findRequiredViewAsType(source, R.id.week_text, "field 'week_text'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShowWeekActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ly_return = null;
    target.week_time = null;
    target.week_studycondition = null;
    target.week_heathcondition = null;
    target.week_returncondition = null;
    target.week_sleepcondition = null;
    target.week_moodcondition = null;
    target.week_consumecondition = null;
    target.week_lovelosecondition = null;
    target.week_conditiontext = null;
    target.week_text = null;
  }
}
