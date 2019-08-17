// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.normalActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IdealActivity_ViewBinding implements Unbinder {
  private IdealActivity target;

  private View view7f090085;

  private View view7f09011b;

  private View view7f090080;

  @UiThread
  public IdealActivity_ViewBinding(IdealActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public IdealActivity_ViewBinding(final IdealActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.img_return, "field 'img_return' and method 'ReturnOnclick'");
    target.img_return = Utils.castView(view, R.id.img_return, "field 'img_return'", ImageView.class);
    view7f090085 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ReturnOnclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_return, "field 'tv_return' and method 'ReturnOnclick'");
    target.tv_return = Utils.castView(view, R.id.tv_return, "field 'tv_return'", TextView.class);
    view7f09011b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ReturnOnclick(p0);
      }
    });
    target.layout = Utils.findRequiredViewAsType(source, R.id.rl_layout, "field 'layout'", RelativeLayout.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.ideal_content, "field 'content'", EditText.class);
    view = Utils.findRequiredView(source, R.id.ideal_sumbit, "field 'button' and method 'sendIdeal'");
    target.button = Utils.castView(view, R.id.ideal_sumbit, "field 'button'", Button.class);
    view7f090080 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendIdeal();
      }
    });
    target.textView_maxnumber = Utils.findRequiredViewAsType(source, R.id.maxnumber, "field 'textView_maxnumber'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    IdealActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.img_return = null;
    target.tv_return = null;
    target.layout = null;
    target.content = null;
    target.button = null;
    target.textView_maxnumber = null;

    view7f090085.setOnClickListener(null);
    view7f090085 = null;
    view7f09011b.setOnClickListener(null);
    view7f09011b = null;
    view7f090080.setOnClickListener(null);
    view7f090080 = null;
  }
}
