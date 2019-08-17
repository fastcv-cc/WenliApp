// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WelcomeActivity_ViewBinding implements Unbinder {
  private WelcomeActivity target;

  @UiThread
  public WelcomeActivity_ViewBinding(WelcomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WelcomeActivity_ViewBinding(WelcomeActivity target, View source) {
    this.target = target;

    target.tv_version = Utils.findRequiredViewAsType(source, R.id.welcome_tv_version, "field 'tv_version'", TextView.class);
    target.activity_layout = Utils.findRequiredViewAsType(source, R.id.welcome_layout, "field 'activity_layout'", RelativeLayout.class);
    target.call_xl = Utils.findRequiredViewAsType(source, R.id.call_xl, "field 'call_xl'", TextView.class);
    target.bt_gohome = Utils.findRequiredViewAsType(source, R.id.gohome, "field 'bt_gohome'", Button.class);
    target.welcome = Utils.findRequiredViewAsType(source, R.id.img_welcome, "field 'welcome'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WelcomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_version = null;
    target.activity_layout = null;
    target.call_xl = null;
    target.bt_gohome = null;
    target.welcome = null;
  }
}
