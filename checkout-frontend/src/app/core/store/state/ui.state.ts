import { Injectable } from "@angular/core";
import { Action, Selector, State } from "@ngxs/store";
import { SetCurrentStep } from "@core/store/actions/ui.actions";

type Notification = {
    type: 'info' | 'error'
    title: string;
    message: string;
    createdAt: Date;
}

export interface UI {
    checkoutSteps: {
        currentStep: number;
    },
    notifications: Notification[]
}

@State<UI>({
    name: 'ui',
    defaults: {
        checkoutSteps: {
            currentStep: 1
        },
        notifications: []    
    }
})
@Injectable({
    providedIn: 'root'
})
export class UIState {

    @Action(SetCurrentStep)
    setCheckoutCurrentStep(ctx: any, action: SetCurrentStep) {
        const state = ctx.getState();
        ctx.setState({
            ...state,
            checkoutSteps: {
                currentStep: action.step
            }
        });
    }

    @Selector()
    static getCheckoutCurrentStep(state: UI) {
        return state.checkoutSteps.currentStep;
    }
}