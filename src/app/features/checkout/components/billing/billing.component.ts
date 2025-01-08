import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { PaymentMethod } from '@core/models/billing.model';
import { SetBillingAction } from '@core/store/actions/set-billing.action';
import { SetCurrentStep } from '@core/store/actions/set-current-step.action';
import { UserState } from '@core/store/state/user.state';
import { Store } from '@ngxs/store';
import { InputTextComponent } from "@shared/components/input-text/input-text.component";
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

@Component({
  selector: 'app-billing',
  imports: [
    BorderIndicatorDirective,
    InputTextComponent,
    CheckoutButtonDirective,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './billing.component.html'
})
export class BillingComponent {
  
  billingForm: FormGroup;

  constructor(
    private store: Store,
    private fb: FormBuilder
  ) {
    const user = this.store.selectSnapshot(UserState.getState);
    this.billingForm = this.fb.group({
      fullname: [user.fullname, Validators.required],
      address: [user.address.street, Validators.required],
      city: [user.address.city, Validators.required],
      cardName: ['', Validators.required],
      cardNumber: ['', Validators.required],
      expirationDate: ['', Validators.required],
      cvv: ['', Validators.required]
    });
    this.store.dispatch(new SetCurrentStep(2))
  }

  onContinue() {
    this.store.dispatch(new SetBillingAction({
      contactInfo: {
        fullname: this.billingForm.value.fullname,
        address: this.billingForm.value.address,
        city: this.billingForm.value.city,
      },
      payment: {
        method: PaymentMethod.CreditCard,
        details: {
          cardName: this.billingForm.value.cardName,
          cardNumber: this.billingForm.value.cardNumber,
          expiration: this.billingForm.value.expirationDate,
          cvv: this.billingForm.value.cvv
        }
      }
    }))
  }

}
