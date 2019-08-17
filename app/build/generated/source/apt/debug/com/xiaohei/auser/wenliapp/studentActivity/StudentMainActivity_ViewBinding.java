// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.studentActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.widget.FreshListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudentMainActivity_ViewBinding implements Unbinder {
  private StudentMainActivity target;

  @UiThread
  public StudentMainActivity_ViewBinding(StudentMainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StudentMainActivity_ViewBinding(StudentMainActivity target, View source) {
    this.target = target;

    target.layout = Utils.findRequiredViewAsType(source, R.id.ll_layout, "field 'layout'", LinearLayout.class);
    target.list_select = Utils.findRequiredViewAsType(source, R.id.list_select, "field 'list_select'", ListView.class);
    target.listview_week = Utils.findRequiredViewAsType(source, R.id.list_week, "field 'listview_week'", FreshListView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.drawer = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawer'", DrawerLayout.class);
    target.img_add = Utils.findRequiredViewAsType(source, R.id.img_add, "field 'img_add'", ImageView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_student_name, "field 'tv_name'", TextView.class);
    target.v_empty = Utils.findRequiredView(source, R.id.la_empty, "field 'v_empty'");
    target.netinfo = Utils.findRequiredViewAsType(source, R.id.net_view_rl, "field 'netinfo'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudentMainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layout = null;
    target.list_select = null;
    target.listview_week = null;
    target.toolbar = null;
    target.drawer = null;
    target.img_add = null;
    target.tv_name = null;
    target.v_empty = null;
    target.netinfo = null;
  }
}
