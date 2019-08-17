// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.normalActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AboutActivity_ViewBinding implements Unbinder {
  private AboutActivity target;

  private View view7f09011b;

  private View view7f090085;

  private View view7f0900be;

  @UiThread
  public AboutActivity_ViewBinding(AboutActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutActivity_ViewBinding(final AboutActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_return, "field 'tv_return' and method 'ReturnOnclick'");
    target.tv_return = Utils.castView(view, R.id.tv_return, "field 'tv_return'", TextView.class);
    view7f09011b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ReturnOnclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_return, "field 'img_return' and method 'ReturnOnclick'");
    target.img_return = Utils.castView(view, R.id.img_return, "field 'img_return'", ImageView.class);
    view7f090085 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ReturnOnclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.photo, "field 'erweima' and method 'showPhoto'");
    target.erweima = Utils.castView(view, R.id.photo, "field 'erweima'", TextView.class);
    view7f0900be = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showPhoto(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AboutActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_return = null;
    target.img_return = null;
    target.erweima = null;

    view7f09011b.setOnClickListener(null);
    view7f09011b = null;
    view7f090085.setOnClickListener(null);
    view7f090085 = null;
    view7f0900be.setOnClickListener(null);
    view7f0900be = null;
  }
}
