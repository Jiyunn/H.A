package kr.happy.myarmy;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by JY on 2017-04-15.
 */

public class ReturnBehavior extends CoordinatorLayout.Behavior<View>{

    private int mDySinceDirectionChange;
    private boolean mIsShowing;
    private boolean mIsHiding;

    public ReturnBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        //스크롤 방향 바뀌면 동작 취소하고 y값 초기화
        if( dy > 0 && mDySinceDirectionChange < 0 ||
                dy <0 && mDySinceDirectionChange > 0) {

            child.animate().cancel(); //취소
            mDySinceDirectionChange =0;
        }

        mDySinceDirectionChange +=dy;

        if (mDySinceDirectionChange > child.getHeight()
                && child.getVisibility() == View.VISIBLE && !mIsShowing) {
            hideView(child);
        } else if (mDySinceDirectionChange  < 0
                && child.getVisibility() == View.GONE && !mIsShowing) {
            showView(child);
        }
    }

    /*
    뷰를 숨김
    아래로 슬라이딩하는 애니메이션
    애니메이션 종료 후 View를 없앰
     */
    private void hideView(final View view) {
        mIsHiding = true;
        ViewPropertyAnimator animator = view.animate()
                .translationY(view.getHeight())
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mIsHiding = false;
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // 취소되면 다시 보여줌
                mIsHiding = false;
                if (!mIsShowing) {
                    showView(view);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

        animator.start();
    }

    /*
      아래서 위로 슬라이딩 애니메이션.
     애니메이션을 시작하기전 View를 보여준다.

     */
    private void showView(final View view) {
        mIsShowing = true;
        ViewPropertyAnimator animator = view.animate()
                .translationY(0)
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mIsShowing = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // 취소되면 다시 숨김
                mIsShowing = false;
                if (!mIsHiding) {
                    hideView(view);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

        animator.start();
    }

}
