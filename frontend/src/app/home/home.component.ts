import { Component, ElementRef, Renderer2, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [],
    templateUrl: './home.component.html',
    styleUrl: './home.component.css'
})
export class HomeComponent implements AfterViewInit {

    constructor(private router: Router, private el: ElementRef, private renderer: Renderer2) {}

    getStarted(): void {

        // Navigate to the login page when the button is clicked
        this.router.navigate(['/auth/login']);
    }

    scrollToPackages() {
        document.querySelector('#insurance-packages')?.scrollIntoView({ behavior: 'smooth' });
    }

    ngAfterViewInit(): void {
        const section = this.el.nativeElement.querySelector('#insurance-packages');
    
        const observer = new IntersectionObserver(
            ([entry]) => {
                if (entry.isIntersecting) {

                    // Animate the Insurance Packages section
                    const cards = section.querySelectorAll('.card');
                    cards.forEach((card: HTMLElement, index: number) => {
                        card.classList.add('animate__animated', 'animate__fadeInUp', `animate__delay-${index-0.5}s`);
                    });

                    // Animate the Get Started button
                    const button = section.querySelector('#get-started-btn');
                    if (button) {
                        button.classList.add('animate__animated', 'animate__fadeInUp', 'animate__delay-1s');
                    }

                    observer.unobserve(section);
                }
            },
            { threshold: 0.2 }
        );
    
        if (section) {
            observer.observe(section);
        }
    }     
}